![reepay.js](https://docs.reepay.com/js/images/logo.png "reepay.js")

Example project demonstrating the use of the Reepay Javascript library.

The project contains a single page web application served by a web server running in a Docker container.

## Prerequisites

Docker and Docker Compose ([Docker Toolbox](https://www.docker.com/products/docker-toolbox))

## Running

1. Define the public key by substituting `{{PUBLIC_KEY}}'` in `index.php`. A public key can be found/generated in the Reepay Administration under Developer -> API credentials.

2. Run a containerized web server using Docker Compose:

    `docker-compose up`

3. In a browser navigate to: http://localhost:8080/



## Testing

Changes to the PHP files will be reflected immediately when edited. Testing different errors can be triggered by using test cards and CVV combinations. See: https://docs.reepay.com/#testing

A list of error codes can be found here: https://docs.reepay.com/js/#errors

## References

[reepay.js](https://docs.reepay.com/js/)

[Reepay API](https://docs.reepay.com/api/)
