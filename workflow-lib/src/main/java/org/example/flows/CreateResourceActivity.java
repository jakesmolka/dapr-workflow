package org.example.flows;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import io.dapr.workflows.runtime.WorkflowActivity;
import io.dapr.workflows.runtime.WorkflowActivityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.flows.createdemographicresource.CreateDemographicResourceInput;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CreateResourceActivity implements WorkflowActivity {

    private final IGenericClient client;
    private final FhirContext fhirContext;

    @Override
    public Object run(WorkflowActivityContext ctx) {
        log.info("Starting Activity: {}", ctx.getName());

        CreateDemographicResourceInput createDemographicResourceInput = ctx.getInput(CreateDemographicResourceInput.class);
        var result = client.create().resource(createDemographicResourceInput.body()).execute();
        String json = fhirContext.newJsonParser().encodeResourceToString(result.getResource());

        log.info("Message Received from input: {}", createDemographicResourceInput.body());
        log.info("Sending message to output: {}", json);

        return json;
    }
}
