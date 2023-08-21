page.dialogs.elements.formCreate.validate({
    rules: {
        fullNameCreate: {
            required: true,
            minlength: 5,
            maxlength: 25
        },
        emailCreate: {
            required: true,
            isEmail: true
        },
        phoneCreate: {
            required: true,
            isNumberWithSpace: true
        }
    },
    messages: {
        fullNameCreate: {
            required: 'Vui lòng nhập họ tên đầy đủ',
            minlength: 'Họ tên tối thiểu là 5 ký tự',
            maxlength: 'Họ tên tối đa là 25 ký tự'
        },
        emailCreate: {
            required: 'Vui lòng nhập email đầy đủ',
        },
        phoneCreate: {
            required: 'Vui lòng nhập phone đầy đủ',
        }
    },
    errorLabelContainer: "#modalCreate .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalCreate .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaCreate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaCreate.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaCreate.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.create();
    }
});
$.validator.addMethod("isEmail", function (value, element) {
    return this.optional(element) || /[a-z]+@[a-z]+\.[a-z]+/.test(value);
}, "Vui lòng nhập đúng định dạng email");

$.validator.addMethod("isNumberWithSpace", function (value, element) {
    return this.optional(element) || /^\s*[0-9,\s]+\s*$/i.test(value);
}, "Vui lòng nhập giá trị số");

page.dialogs.elements.formUpdate.validate({
    rules: {
        fullNameUpdate: {
            required: true,
            minlength: 5,
            maxlength: 25
        },
        emailUpdate: {
            required: true,
            isEmail: true
        },
        phoneUpdate: {
            required: true,
            isNumberWithSpace: true
        }
    },
    messages: {
        fullNameUpdate: {
            required: 'Vui lòng nhập họ tên đầy đủ',
            minlength: 'Họ tên tối thiểu là 5 ký tự',
            maxlength: 'Họ tên tối đa là 25 ký tự'
        },
        emailUpdate: {
            required: 'Vui lòng nhập email đầy đủ',
        },
        phoneUpdate: {
            required: 'Vui lòng nhập phone đầy đủ',
        }
    },
    errorLabelContainer: "#modalUpdate .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalUpdate .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaUpdate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaUpdate.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaUpdate.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.update();
    }
});
page.dialogs.elements.formDeposit.validate({
    rules: {
        transactionDeposit: {
            required: true,
            number: true,
            maxlength: 15
        }
    },
    messages: {
        transactionDeposit: {
            required: 'Số tiền không được để trống',
            number: 'Vui lòng nhập số',
            maxlength: 'Tối đa 15 chữ số'
        }
    },
    errorLabelContainer: "#modalDeposit .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalDeposit .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaDeposit.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaDeposit.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaDeposit.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.deposit();
    }
});
page.dialogs.elements.formWithdraw.validate({
    rules: {
        transactionWithdraw: {
            required: true,
            number: true,
            maxlength: 15,
        }
    },
    messages: {
        transactionWithdraw: {
            required: 'Số tiền không được để trống',
            number: 'Vui lòng nhập số',
            maxlength: 'Tối đa 15 chữ số',
        }
    },
    errorLabelContainer: "#modalWithdraw .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalWithdraw .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaWithdraw.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaWithdraw.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaWithdraw.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.withdraw();
    }
});
page.dialogs.elements.formTransfer.validate({
    rules: {
        transferAmount: {
            required: true,
            number: true,
            maxlength: 15,
        }
    },
    messages: {
        transferAmount: {
            required: 'Số tiền không được để trống',
            number: 'Vui lòng nhập số',
            maxlength: 'Tối đa 15 chữ số'
        }
    },
    errorLabelContainer: "#modalTransfer .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalTransfer .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaTransfer.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaTransfer.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaTransfer.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.transfer();
    }
});
