package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should accept a new word on post"

    request {
        url "/api/v1/translation"
        method POST()
        headers {
            contentType applicationJson()
        }
        body (
                word: "Neu",
                translation: "New"
        )
    }

    response {
        status OK()
    }
}
