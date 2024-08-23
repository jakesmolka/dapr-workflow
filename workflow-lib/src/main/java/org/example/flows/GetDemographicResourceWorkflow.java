package org.example.flows;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;

public class GetDemographicResourceWorkflow extends Workflow {
    @Override
    public WorkflowStub create() {
        return ctx -> {
            ctx.getLogger().info("Starting Workflow: " + ctx.getName());

            String result = "";
            result += ctx.callActivity(ToUpperCaseActivity.class.getName(), "Tokyo", String.class).await() + ", ";
            result += ctx.callActivity(ToUpperCaseActivity.class.getName(), "London", String.class).await() + ", ";
            result += ctx.callActivity(ToUpperCaseActivity.class.getName(), "Seattle", String.class).await();

            ctx.getLogger().info("Workflow finished with result: " + result);
            ctx.complete(result);
        };
    }
}
