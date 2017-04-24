$.getScript("js/texts.js");

function ReepayUtils(valSettings) {
    var self = this;
    // Settings which can extend/overwrite defaults by parsing an object as a parameter. See the HTML for example
    // Everything but the functions in the return block is private
    self.validationSettings = {
        cvvId: "#cvv",
        cardnumberId: "#cardnumber",
        expiryId: "#expiry",
        inputClass: ".customer_info",
        termsId: "#terms",
        formId: "#paymentform",
        couponId: "#coupon",
        checkEmail: true,
        submitProcess: false,
        locale: "en_GB"
    };
    $.extend(self.validationSettings, valSettings);

    // Validating that all required input fields are filled out
    self.validateInput = function(inputName) {
        $(inputName).each(function() {
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
    }

    // Check if the email fits the standard emai format
    self.isEmail = function(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    // Validate that the customer info fields has been filled out
    self.validateCustomer = function(inputName) {
        var status = true;

        $(inputName).each(function() {

            if ($(this).attr('required')) {
                if ($(this).val() == "") {
                    console.log($(this).attr("name") + " is empty");
                    status = false;
                    return;
                }
            }

            if ($(this).attr("name") == "email" && !self.isEmail($(this).val())) {
                console.log($(this).attr("name") + " is not valid");
                status = false;
                return;
            }
        });
        return status;
    }
    self.getCustomer = function(inputName) {

        var customer = { inputName };

        $(".customer_info").each(function() {
            customer[$(this).attr("name")] = $(this).val();
        });

        return customer;
    }

    // Card number validation on HTML fields
    self.validateCardnumber = function(cardnumberId) {
        $(cardnumberId).blur(function() {
            $('#error').hide();
            if (!reepay.validate.cardNumber($(cardnumberId).val())) {
                // This can be chamged to how you wish to do with the validation of the cardnumberId

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
    };
    self.validateExpiry = function(expiryId) {
        $(expiryId).blur(function() {
            var exp = $(expiryId).val().replace(/\s/g, '').split("/");
            if (!reepay.validate.expiry(exp[0], exp[1])) {
                // This can be chamged to how you wish to do with the validation of the cardnumberId
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
    };
    self.validateCvv = function(cvvId) {
        $(cvvId).blur(function() {
            if (!reepay.validate.cvv($(cvvId).val())) {
                // This can be changed to how you wish to do with the validation of the cardnumberId
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
    }

    self.validateTerms = function(termsId) {
        $(termsId).change(function() {
            // Check if terms has been accepted. This can be changed to how you wish to handle the errors.
            if (this.checked) {
                $("#terms-group").removeClass("has-error");
                $("#terms-group").addClass("has-success");
            } else {
                $("#terms-group").removeClass("has-success");
                $("#terms-group").addClass("has-error");
            }
        });
    }

    // The public validate function
    self.validate = function(settings) {
        self.validateInput(settings.inputClass);

        if (settings.checkEmail) {
            $("input[name=email]").blur(function() {
                if (!self.isEmail($(this).val())) {
                    $(this).parent().removeClass("has-success");
                    $(this).parent().addClass("has-error");
                } else {
                    $(this).parent().removeClass("has-error");
                    $(this).parent().addClass("has-success");
                }

            });
        }
        self.validateCustomer(settings.inputClass);
        self.validateCardnumber(settings.cardnumberId);
        self.validateExpiry(settings.expiryId);
        self.validateCvv(settings.cvvId);
        self.validateTerms(settings.termsId);
    }

    self.submitForm = function(settings, tokenHandle) {
        $(settings.formId).on('submit', function(event) {
            var form = this;
            event.preventDefault();

            // Validate Customer
            if (!self.validateCustomer(settings.inputClass)) {
                // Show some kind of error here
                $('#customer_error_headline').html("Wrong information");
                $('#customer_error_message').html("Please fill in correct information");
                $("#customer_error").show();

                return;
            }

            self.customer = self.getCustomer(settings.inputClass);

            $("#customer_error").hide();

            var exp = $(settings.expiryId).val().replace(/\s/g, '').split("/");

            // alternatively..
            var paymentinfo = {
                // TODO: the name must be cardnumber not only number. Server supports number fix.
                number: $(settings.cardnumberId).val(),
                month: exp[0],
                year: exp[1],
                cvv: $(settings.cvvId).val()
            };
            reepay.validate.cardNumber(paymentinfo['number']);

            var $btn = $('#savebutton').button('loading');

            if (!$(settings.termsId)[0].checked) {
                // This should be changed to suit your needs and your HTML
                $("#terms-group").addClass("has-error");

                $('#error_headline').html("Accept terms");
                $('#error_message').html("Remember to accept terms");
                $('#error').show();

                $btn.button('reset');
                return;
            }

            reepay.token(paymentinfo, function(err, token) {
                // Handle token error and success here. This is just an example.
                // Should be changed to suit your needs and your HTML.
                if (err) {
                    console.log("An error happened: code: " + err.code + " message: " + err.message);
                    var errText = interpretError(err, settings.locale);
                    var headline = errText.headline;
                    var message = errText.message;

                    $('#error_headline').html(headline);
                    if (err.fields) {
                        message = message + err.fields;
                    }
                    $('#error_message').html(message);
                    $('#error').show();
                    $btn.button('reset');
                } else if (tokenHandle == undefined) {
                    $("[data-reepay=token]").val(token.id);
                    form.submit();
                } else {
                    tokenHandle(self.customer, token);
                }
            });
        });
    };

    self.couponCheck = function(couponId) {
        $(couponId).on('blur', function(event) {
            var coupon = {
                code: $(couponId).val()
            }

            if (coupon.code.startsWith("DISCOUNT")) {
                $(couponId).parent().removeClass("has-error");
                $(couponId).parent().toggleClass("has-success");
                $('.validation-message').html('<span class="success">Valid</span>');
            } else {
                $(couponId).parent().removeClass("has-success");
                $(couponId).parent().addClass("has-error");
                $('.validation-message').html('<span class="error">Not valid</span>');
            }
            /*
            $.ajax({
                url: 'https://api.reepay.com/v1/coupon/code/validate',
                method: 'GET',
                data: JSON.stringify(coupon),
                dataType: 'application/json',
            }).done(function(data){
                $('input[name=code]').parent().removeClass("has-error");
                $('input[name=code]').parent().toggleClass("has-success");
            }).fail(function(err){
                $('input[name=code]').parent().removeClass("has-success");
                $('input[name=code]').parent().addClass("has-error");
            });
            */
        });
    }



    // Public to call
    return {
        // Validates input with the given settings in the constructor
        validate: function() {
            self.validate(self.validationSettings);
        },
        reepaySubmit: function(tokenHandle) {
            self.submitForm(self.validationSettings, tokenHandle);
        },
        coupon: function() {
            self.couponCheck(self.validationSettings.couponId);
        },
        utilSettings: self.validationSettings
    }
}