package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return list of translations"

    request {
        url "/api/v1/translation"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                id: 1,
                word: "Hallo",
                translation: "Hello"
        )
    }
}
