<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reepay sign-up page example</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/app.css">
</head>

<body>
    <div class="container-fluid">

        <div class="row">

            <div class="col-md-3"></div>
            <div class="col-md-6" style="padding-top:20px;">


                <section class="panel panel-default portlet-item">
                    <header class="panel-heading">
                        <h2 class="panel-title">Sign-up</h2>
                    </header>
                    <section class="panel-body">
                        <h4>Subscription</h4>
                        <div class="row">
                            <div class="col-sm-12">
                                <p>Gold product - $70 / month</p>
                            </div>
                            <div class="col-sm-12">
                                <p>Enter your information and sign-up</p>
                            </div>
                        </div>
                        <br/>
                        <form id="paymentform" role="form" method="post" type="submit" action="signup.php">
                            <article class="media" id="paymentinfo">
                                <h4>Personal information</h4>

                                <div class="alert alert-danger" style="display: none" id="customer_error">
                                    <i class="fa fa-ban-circle"></i>
                                    <strong id="customer_error_headline">Oh snap!</strong>
                                    <p id="customer_error_message"></p>
                                </div>

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="control-label">First name</label>
                                            <input type="text" class="form-control customer_info" name="first_name" required="required" placeholder="First name">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="control-label">Last name</label>
                                            <input type="text" class="form-control customer_info" name="last_name" required="required" placeholder="Last name">
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="control-label">Email</label>
                                            <input type="text" class="form-control customer_info" name="email" required="required" placeholder="Email">
                                        </div>
                                    </div>
                                </div>

                                <h4>Payment information</h4>

                                <div class="alert alert-danger" style="display: none" id="error">
                                    <i class="fa fa-ban-circle"></i>
                                    <strong id="error_headline">Oh snap!</strong>
                                    <p id="error_message"></p>
                                </div>
                                <div class="media-body">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group" id="form-group-cardnumber">
                                                <label class="control-label">Card number</label>
                                                <input type="text" class="form-control" placeholder="Enter cardnumber" id="cardnumber">
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <div class="form-group" id="form-group-exp">
                                                        <label class="control-label">Expiry date</label>
                                                        <input type="text" class="form-control" placeholder="MM/YY" id="expiry">
                                                    </div>
                                                </div>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-7">
                                                    <div class="form-group" id="form-group-cvc">
                                                        <label class="control-label">CVV</label>
                                                        <input type="text" class="form-control" placeholder="CVV" id="cvc">
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="terms-group" class="form-group">
                                                <div class="checkbox">
                                                    <label>
                                                    <input type="checkbox" id="terms"/>
                                                    I accept
                                                    <a href="#" data-toggle="modal" data-target="#myModal">the terms.</a>
                                                </label>
                                                </div>
                                            </div>
                                            <input type="hidden" id="token" name="reepay-token" data-reepay="token">
                                            <button class="btn btn-s-md btn-primary" style="width:100%" data-loading-text="sending..." id="savebutton">Sign-up
                                        </button>


                                        </div>
                                        <div class="col-sm-6 hidden-xs" id="card-container"></div>
                                    </div>
                                </div>
                            </article>
                        </form>
                        <article class="media" id="paymentinfo_success" style="display: none">
                            <div class="media-body" style="height: 200px;">
                                <p></p>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="box">
                                            <div class="box-icon">
                                                <span class="fa fa-4x fa-check"></span>
                                            </div>
                                            <div class="info">
                                                <h5 class="text-center">You are now signed up</h5>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-sm-6 hidden-xs"></div>
                                </div>
                            </div>
                        </article>
                    </section>
                </section>
                <script src="/js/jquery.min.js"></script>
                <script src="/js/button.js"></script>
                <script src="/js/jquery.card.js"></script>
                <script src="/js/bootstrap.min.js"></script>
                <script src="/js/texts.js"></script>
                <script src="https://js.reepay.com/v1/reepay.js"></script>

                <script>
                    $('#paymentform').card({
                        container: '#card-container',
                        formSelectors: {
                            numberInput: 'input#cardnumber',
                            expiryInput: 'input#expiry',
                            cvcInput: 'input#cvc',
                            nameInput: 'input#name'
                        },
                        values: {
                            number: '**** **** **** ****',
                            expiry: '**/**',
                            cvc: '***'
                        }
                    }); 

                    var locale = "en_GB";

                    // Validate Customer info

                    $(".customer_info").each(function() {

                        if ($(this).attr('required')) {

                            $(this).blur(function() {

                                if ($(this).val() == "") {
                                    $(this).parent().removeClass("has-success");
                                    $(this).parent().addClass("has-error");
                                } else {
                                    $(this).parent().removeClass("has-error");
                                    $(this).parent().addClass("has-success");
                                }

                            });

                        }

                    });

                    // Additional validation on email

                    function isEmail(email) {
                        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                        return regex.test(email);
                    }

                    $("input[name=email]").blur(function() {

                        if (!isEmail($(this).val())) {
                            $(this).parent().removeClass("has-success");
                            $(this).parent().addClass("has-error");
                        } else {
                            $(this).parent().removeClass("has-error");
                            $(this).parent().addClass("has-success");
                        }

                    });

                    function validateCustomer() {

                        var status = true;

                        $(".customer_info").each(function() {

                            if ($(this).attr('required')) {
                                if ($(this).val() == "") {
                                    console.log($(this).attr("name") + " is empty");
                                    status = false;
                                    return;
                                }
                            }

                            if ($(this).attr("name") == "email" && !isEmail($(this).val())) {
                                console.log($(this).attr("name") + " is not valid");
                                status = false;
                                return;
                            }
                        });


                        return status;
                    }

                    function getCustomer() {

                        var customer = {};

                        $(".customer_info").each(function() {
                            customer[$(this).attr("name")] = $(this).val();
                        });

                        return customer;
                    }


                    // Validate cardnumber
                    $('#cardnumber').blur(function() {
                        $('#error').hide();
                        if (!reepay.validate.cardNumber($('#cardnumber').val())) {
                            if ($('#form-group-cardnumber').hasClass("has-success")) {
                                $('#form-group-cardnumber').toggleClass("has-success");
                            }
                            if (!$('#form-group-cardnumber').hasClass("has-error")) {
                                $('#form-group-cardnumber').toggleClass("has-error");
                            }
                        } else {
                            if ($('#form-group-cardnumber').hasClass("has-error")) {
                                $('#form-group-cardnumber').toggleClass("has-error");
                            }
                            if (!$('#form-group-cardnumber').hasClass("has-success")) {
                                $('#form-group-cardnumber').toggleClass("has-success");
                            }
                        }
                    });

                    // Validate expiry
                    $('#expiry').blur(function() {
                        var exp = $("#expiry").val().replace(/\s/g, '').split("/");
                        if (!reepay.validate.expiry(exp[0], exp[1])) {
                            if ($('#form-group-exp').hasClass("has-success")) {
                                $('#form-group-exp').toggleClass("has-success");
                            }
                            if (!$('#form-group-exp').hasClass("has-error")) {
                                $('#form-group-exp').toggleClass("has-error");
                            }
                        } else {
                            if ($('#form-group-exp').hasClass("has-error")) {
                                $('#form-group-exp').toggleClass("has-error");
                            }
                            if (!$('#form-group-exp').hasClass("has-success")) {
                                $('#form-group-exp').toggleClass("has-success");
                            }
                        }
                    });

                    // Validate cvv
                    $('#cvc').blur(function() {
                        if (!reepay.validate.cvv($('#cvc').val())) {
                            if ($('#form-group-cvc').hasClass("has-success")) {
                                $('#form-group-cvc').toggleClass("has-success");
                            }
                            if (!$('#form-group-cvc').hasClass("has-error")) {
                                $('#form-group-cvc').toggleClass("has-error");
                            }
                        } else {
                            if ($('#form-group-cvc').hasClass("has-error")) {
                                $('#form-group-cvc').toggleClass("has-error");
                            }
                            if (!$('#form-group-cvc').hasClass("has-success")) {
                                $('#form-group-cvc').toggleClass("has-success");
                            }
                        }
                    });

                    $('#terms').change(function() {
                        if (this.checked) {
                            $("#terms-group").removeClass("has-error");
                            $("#terms-group").addClass("has-success");
                        } else {
                            $("#terms-group").removeClass("has-success");
                            $("#terms-group").addClass("has-error");
                        }
                    });

                    reepay.configure("1{{PUBLIC_KEY}}");

                    $('#paymentform').on('submit', function(event) {
                        var form = this;
                        event.preventDefault();

                        // Validate Customer
                        if (!validateCustomer()) {
                            // show customer error

                            $('#customer_error_headline').html("Wrong information");
                            $('#customer_error_message').html("Please fill in correct information");
                            $("#customer_error").show();

                            return;
                        }
                        $("#customer_error").hide();


                        // Create customer object

                        var customer = getCustomer();


                        var exp = $("#expiry").val().replace(/\s/g, '').split("/");

                        // alternatively..
                        var paymentinfo = {
                            // TODO: the name must be cardnumber not only number. Server supports number fix.
                            number: $('#cardnumber').val(),
                            month: exp[0],
                            year: exp[1],
                            cvv: $('#cvc').val()
                        };

                        reepay.validate.cardNumber(paymentinfo['cardnumber']);

                        var $btn = $('#savebutton').button('loading');

                        if (!$('#terms')[0].checked) {
                            $("#terms-group").addClass("has-error");

                            $('#error_headline').html("Accept terms");
                            $('#error_message').html("Remember to accept terms");
                            $('#error').show();

                            $btn.button('reset');
                            return;
                        }

                        reepay.token(paymentinfo, function(err, token) {
                            if (err) {
                                console.log("An error happened: code: " + err.code + " message: " + err.message);
                                var errText = interpretError(err, locale);
                                var headline = errText.headline;
                                var message = errText.message;

                                $('#error_headline').html(headline);
                                if (err.fields) {
                                    message = message + err.fields;
                                }
                                $('#error_message').html(message);
                                $('#error').show();
                                $btn.button('reset');
                            } else {

                                $("[data-reepay=token]").val(token.id);
                                 form.submit();

                            }
                        });
                    });
                </script>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6" style="text-align:center;top:-15px">
                <small>Powered by <a href="https://reepay.com" target="_blank">reepay.com</a>.</small>
            </div>
            <div class="col-md-3"></div>
        </div>

    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                    <h4 class="modal-title" id="myModalLabel">Terms</h4>
                </div>
                <div class="modal-body">
                    <div>
                        <p>Terms...</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
