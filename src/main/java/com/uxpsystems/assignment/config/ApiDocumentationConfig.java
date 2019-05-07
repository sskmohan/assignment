package com.uxpsystems.assignment.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "User Resources",
                version = "V12.0.12",
                title = "User Resource API",
                contact = @Contact(
                   name = "Sai Krishna Mohan", 
                   email = "kris78@gmail.com", 
                   url = "http://www.gmail.com"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/json"},
        produces = {"application/json", "application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://www.amdocs.com")
)
public interface ApiDocumentationConfig {

}