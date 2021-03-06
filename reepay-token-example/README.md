![Reepay Token](https://docs.reepay.com/token/images/logo.png "Reepay Token")

Example project demonstrating the use of the [Reepay Token](https://docs.reepay.com/token/) solution to get a token representing payment information (e.g. credit card) that can be used to create one-time charges and subscriptions.

The project contains some example payment and subscription sign-up pages. All the pages submit data to a simple PHP web application served by a web server running in a Docker container. The PHP application just dumps the parameters received. 

The paramters can be used to perform a single charge, see this [example](https://github.com/reepay/reepay-examples/wiki/One-time-charging). Or it can be used to create a subscription, see this [example](https://github.com/reepay/reepay-examples/wiki/Simple-subscription-handling#create-customer-and-subscription).

## Prerequisites

Docker and Docker Compose ([Docker Toolbox](https://www.docker.com/products/docker-toolbox))

## Running

1. Define the public key by substituting `pub_11111111111111111111111111111111` in the HTML files. A public key can be found/generated in the Reepay Administration under Developer -> API credentials.

2. Run a containerized web server using Docker Compose:

    `docker-compose up`

3. In a browser navigate to: http://localhost:8080/index.html

4. Try and modify the examples. Changes to the HTML file will be reflected immediately when edited. Testing different errors can be triggered by using test cards and CVV combinations. See: https://docs.reepay.com/api/#testing.

## The examples

### Simple charge and sign-up

The files `simple_signup.html` and `simple_charge.html` shows the simplest configuration. We gennerate the button and the hidden input field for you. All you have to make sure to do is pick up the generated value in your form. Example below for a sign-up.

1. Include the script in your form in the place you want us to generate our button.
    ```html
    <script src="https://token.reepay.com/token.js"
        class="reepay-button"
        data-pubkey="pub_11111111111111111111111111111111"
        data-text="Sign-up"
        data-language="en"
        data-recurring="true">
    </script>
    ```
    You can omit the language if you want to use the English default.

2. You're done! If you're simply posting a regular form, the value should be picked up as `reepay-token`. See [documentation](https://docs.reepay.com/token/) for additional values that can be picked up. E.g. masked card number.

### Custom

We know that some of our customers value the ability to customize stuff. So we made it possible to configure our frame in a more custom way. You can see examples in `custom_signup.html` and `custom_charge.html`.

1. Include the script
    ```html
    <script src="https://token.reepay.com/token.js"></script>
    ```
2. Instantiate the handler with a configure object. Here you can also bind callbacks. The `token` is what will happen when you receive the token and the `ready` is if you want something to happen when our frame is ready to be opened. The bind of the click event listener is the binding to the open button so you can actually open our frame.
    ```html
        <form id="signupform">
            <input type="hidden" id="token" name="reepay-token"/>
            <input type="submit" id="signup-button" disabled="disbaled" value="Sign-up"/>
        </form>
        <script>
        var handler = reepaytoken.configure({
            key: 'pub_11111111111111111111111111111111',
            language: 'da',
            token: function(result) {
                console.log(JSON.stringify(result));
                document.querySelector('#token').value = result.token;
                document.getElementById('signupform').submit();
            },
            ready: function() {
                document.querySelector('#signup-button').removeAttribute('disabled');
            },
            close: function() {
                console.log('Modal closed');
            }
        });
        document.querySelector('#signup-button').addEventListener('click', function(event) {
            event.preventDefault();
            handler.open();
        });
        </script>
    ```


## References

[Reepay Token](https://docs.reepay.com/token/)

[Reepay API](https://docs.reepay.com/api/)

[Subscription Use Case](https://github.com/reepay/reepay-examples/wiki/Simple-subscription-handling)

[Charging Use Case](https://github.com/reepay/reepay-examples/wiki/One-time-charging)
