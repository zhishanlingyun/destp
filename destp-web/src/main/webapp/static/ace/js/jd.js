
function checkAndSubmitOrder(d, l, e) {
    if (l) {
        $("#paymentId").val(l)
    } else {
        l = $("#paymentId").val()
    }
    var j = $("#isUsedVirtualPay").val();
    var i = "";
    if ($("#securityPayPassword").val()) {
        i = $("#securityPayPassword").val()
    }
    if ($("#stockStatus").val() != "2") {
        openStockWindow();
        return
    } else {
        if (j == "true" && i == "") {
            virtualPayHandler();
            return
        }
    }
    var b = $("#sid").val();
    var c = $("#pageSnapshotId").val();
    var m = $("#flowTypeId").val() || $("#flowTypeId").val() == 0 ? $("#flowTypeId").val() : "";
    var f = $("#isMixPayMentId").val();
    var a = "/norder/checkAndSubmitOrder.json?fm=order";
    if ($("#orderKeyUrlId").val()) {
        MRefreshCheck.init({
            checkArray: $("#orderArrayId").val(),
            checkDate: $("#orderDateId").val()
        });
        var h = MRefreshCheck.createRandom();
        var g = new Array("&", "m", "R", "d", "m", "=");
        var o = g[0] + g[1] + g[2];
        if ($("#orderKeyId").val() == "true") {
            o += g[3] + g[4] + g[5]
        } else {
            o += g[4] + g[3] + g[5]
        }
        a += o + h
    }
    if ($("#isOpenDefenceUrlId").val()) {
        var k = $("#submitOrderStr").val();
        if (k == null) {
            k = "shangpinshuxing"
        }
        a += "&tk1=" + k
    }
    $.ajax({
        url: a,
        type: "POST",
        dataType: "json",
        data: {
            sid: b,
            pageSnapshotId: c,
            flowType: m,
            "order.paymentId": l,
            mixPayMent: f,
            "order.securityPayPassword": i,
            ignoreSKUPriceIncrease: e
        },
        success: function(t, q, u) {
            var p = u.getResponseHeader("jumpurl");
            if (p) {
                window.location.href = p
            }
            if (j == "true") {
                $(".confirm-w").hide();
                $(".popup-w").hide()
            }
            if (t.placedOrder) {
                messageDialog = t.messageDialog;
                openMessageDialog(2, messageDialog.message, messageDialog.buttonInfos);
                return
            } else {
                if (t.isPriceChange == true) {
                    var s = t.priceChangeCommodities;
                    openPriceChangeToast(s);
                    return
                } else {
                    if (t.needPassword == true) {
                        $("#isUsedVirtualPay").val(t.needPassword);
                        if ($("#openPwdWindow")) {
                            virtualPayHandler();
                            return
                        }
                        $("#isOpenPaymentPassword").val("true");
                        virtualPayHandler();
                        return
                    } else {
                        if (t.shipFeeRise) {
                            messageDialog = t.messageDialog;
                            openShipFeeRiseToast(messageDialog.message, messageDialog.buttonInfos);
                            return
                        }
                    }
                }
            }
            if (t.flag) {
                var r = t.nextPage;
                $("#isUsedVirtualPay").val("");
                setTimeout(location.href = r, 100)
            } else {
                if (t.messageDialog) {
                    messageDialog = t.messageDialog;
                    openMessageDialog(1, messageDialog.message, messageDialog.buttonInfos, j);
                    return
                }
            }
        }
    });
    var n = $("#enterType").val();
    if (n == "A" || n == "B") {
        setTimeout(function() {
                pingClick(d, "MNeworder_Submit", n, "", "")
            },
            0)
    } else {
        if (n == "C") {
            setTimeout(function() {
                    pingClick(d, "MNeworder_CSubmit", "", "", "")
                },
                0);
            if (l == 4) {
                setTimeout(function() {
                        pingClick(d, "MNeworder_COnlinePay", "", "", "")
                    },
                    0)
            } else {
                if (l == 1) {
                    setTimeout(function() {
                            pingClick(d, "MNeworderAddress_CCashOnDeliveryAble", "", "", "")
                        },
                        0)
                } else {
                    setTimeout(function() {
                            pingClick(d, "MNeworder_COnlinePay", "", "", "")
                        },
                        0)
                }
            }
        } else {
            setTimeout(function() {
                    pingClick(d, "MNeworder_Submit", "A", "", "")
                },
                0)
        }
    }
}