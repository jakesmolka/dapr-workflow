package org.example.flows.getdemographicresource;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;
import lombok.extern.slf4j.Slf4j;
import org.example.flows.GetResourceActivity;
import org.example.flows.ToUpperCaseActivity;

@Slf4j
public class GetDemographicResourceWorkflow extends Workflow {
    @Override
    public WorkflowStub create() {
        return ctx -> {
            log.info("Starting Workflow: {}", ctx.getName());

            GetDemographicResourceInput getDemographicResourceInput = ctx.getInput(GetDemographicResourceInput.class);

            String json = ctx.callActivity(GetResourceActivity.class.getName(), getDemographicResourceInput, String.class).await();

            log.info("Workflow finished with result: {}", json);
            ctx.complete(json);
        };
    }
}
