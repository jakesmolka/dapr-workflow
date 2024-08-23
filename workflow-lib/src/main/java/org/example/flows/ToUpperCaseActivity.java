package org.example.flows;

import io.dapr.workflows.runtime.WorkflowActivity;
import io.dapr.workflows.runtime.WorkflowActivityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToUpperCaseActivity implements WorkflowActivity {

    @Override
    public Object run(WorkflowActivityContext ctx) {
        Logger logger = LoggerFactory.getLogger(ToUpperCaseActivity.class);
        logger.info("Starting Activity: " + ctx.getName());

        var message = ctx.getInput(String.class);
        var newMessage = message.toUpperCase();

        logger.info("Message Received from input: " + message);
        logger.info("Sending message to output: " + newMessage);

        return newMessage;
    }
}
