package org.example.flows.createdemographicresource;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;
import lombok.extern.slf4j.Slf4j;
import org.example.flows.CreateResourceActivity;

@Slf4j
public class CreateDemographicResourceWorkflow extends Workflow {
    @Override
    public WorkflowStub create() {
        return ctx -> {
            log.info("Starting Workflow: {}", ctx.getName());

            CreateDemographicResourceInput createDemographicResourceInput = ctx.getInput(CreateDemographicResourceInput.class);

            String result = ctx.callActivity(CreateResourceActivity.class.getName(), createDemographicResourceInput, String.class).await();

            log.info("Workflow finished with result: {}", result);
            ctx.complete(result);
        };
    }
}
