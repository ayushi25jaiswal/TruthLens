from pathlib import Path

import torch
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from transformers import AutoModelForSequenceClassification, AutoTokenizer

app = FastAPI(title="TruthLens ML API", version="1.0.0")

MODEL_DIR = Path(__file__).parent / "truthlens-distilbert-model"

if not MODEL_DIR.exists():
    raise RuntimeError(f"Model directory not found: {MODEL_DIR}")

tokenizer = AutoTokenizer.from_pretrained(str(MODEL_DIR))
model = AutoModelForSequenceClassification.from_pretrained(str(MODEL_DIR))
model.eval()


class PredictionRequest(BaseModel):
    claim: str = Field(min_length=1, max_length=1000)
    evidence: str = Field(min_length=1, max_length=3000)


@app.get("/health")
def health():
    return {"status": "ok", "model_loaded": True}


@app.post("/predict")
def predict(request: PredictionRequest):
    try:
        inputs = tokenizer(
            request.claim,
            request.evidence,
            return_tensors="pt",
            truncation=True,
            max_length=256,
        )

        with torch.inference_mode():
            outputs = model(**inputs)
            probabilities = torch.softmax(outputs.logits, dim=-1)[0]

        predicted_id = int(torch.argmax(probabilities).item())
        label = model.config.id2label[predicted_id]

        return {
            "claim": request.claim,
            "evidence": request.evidence,
            "prediction": label,
            "model_score": round(float(probabilities[predicted_id].item()), 4),
            "note": (
                "This is a model score for the predicted class, "
                "not a calibrated probability that the claim is true."
            ),
        }

    except Exception as exc:
        raise HTTPException(
            status_code=500,
            detail=f"Prediction failed: {type(exc).__name__}",
        ) from exc
