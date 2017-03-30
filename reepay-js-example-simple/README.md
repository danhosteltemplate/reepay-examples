![reepay.js](https://docs.reepay.com/js/images/logo.png "reepay.js")

Example project demonstrating the use of the Reepay Javascript library to make a sign-up page.

The project contains a simple PHP web application served by a web server running in a Docker container. The sign-up page submits data to the server. The data can be used to create a customer and a subscription. See this [example](https://github.com/reepay/reepay-examples/wiki/Simple-subscription-handling#create-customer-and-subscription).

## Prerequisites

Docker and Docker Compose ([Docker Toolbox](https://www.docker.com/products/docker-toolbox))

## Running

1. Define the public key by substituting `{{PUBLIC_KEY}}'` in `index.php`. A public key can be found/generated in the Reepay Administration under Developer -> API credentials.

2. Run a containerized web server using Docker Compose:

    `docker-compose up`

3. In a browser navigate to: http://localhost:8080/


## Testing

Changes to the PHP files will be reflected immediately when edited. Testing different errors can be triggered by using test cards and CVV combinations. See: https://docs.reepay.com/api/#testing

A list of error codes can be found here: https://docs.reepay.com/js/#errors

## References

[reepay.js](https://docs.reepay.com/js/)

[Reepay API](https://docs.reepay.com/api/)

[Use case](https://github.com/reepay/reepay-examples/wiki/Simple-subscription-handling)
