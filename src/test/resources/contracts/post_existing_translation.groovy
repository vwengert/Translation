package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should reject when adding an existing word on post"

    request {
        url "/api/v1/translation"
        method POST()
        headers {
            contentType applicationJson()
        }
        body (
                word: "Existiert",
                translation: "Exists"
        )
    }

    response {
        status BAD_REQUEST()
    }
}
