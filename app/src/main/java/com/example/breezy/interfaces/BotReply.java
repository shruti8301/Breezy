package com.example.breezy.interfaces;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;

public interface BotReply {
    void callback(DetectIntentResponse returnResponse);
}