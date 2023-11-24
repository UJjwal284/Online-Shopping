$(document).ready(function () {
    $("#searchButton").click(function () {
        performSearch();
    });

    $("#searchInput").on("input", function () {
        performSearch();
    });

    function performSearch() {
        var searchTerm = $("#searchInput").val().toLowerCase();
        filterProductsByName(searchTerm);
    }

    function filterProductsByName(searchTerm) {
        $("#productTableBody tr").hide().filter(function () {
            return $(this).text().toLowerCase().includes(searchTerm);
        }).show();

        $("#cartProductTableBody tr").hide().filter(function () {
            return $(this).text().toLowerCase().includes(searchTerm);
        }).show();
    }

    $(".addToCart").click(function () {
        var buttonId = $(this).attr('id');
        addToCart(buttonId);
    });

    $(".removeFromCart").click(function () {
        var buttonId = $(this).attr('id');
        removeFromCart(buttonId);
    });

    function addToCart(buttonId) {
        var apiUrl = 'http://localhost:8080/cart/5/' + buttonId;
        fetch(apiUrl, {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .then(getCart)
            .catch(error => {
                console.error('Error:', error);
            })
    }

    function removeFromCart(buttonId) {
        var apiUrl = 'http://localhost:8080/cart/5/' + buttonId;
        fetch(apiUrl, {
            method: 'DELETE', headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .then(getCart)
            .catch(error => {
                console.error('Error:', error);
            })
    }

    function getCart() {
        var apiUrl = 'http://localhost:8080/cart/5';
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                $("#cart").text("Cart(" + data.products.length + ")")

                let sumQuantity = 0;
                let sumTotal = 0;
                $("#cartProductTableBody").empty();
                $.each(data.products, function (index, product) {
                    var row = '<tr class="product-card" data-product-id="' + product.product.id + '">' + '<td><img src="product.PNG" alt="Product Image" width="100" height="100"/></td>' + '<td><strong>' + product.product.name + '</strong></td>' + '<td class="description">' + product.product.description + '</td>' + '<td>' + product.product.price + '</td>' + '<td>' + product.quantity + '</td>' + '<td>' + product.total + '</td>' + '<td>' + '<button class="btn btn-outline-warning addToCart" data-action="add">Add More</button>' + '<button class="btn btn-outline-danger removeFromCart mt-2" data-action="remove">Remove</button>' + '</td>' + '</tr>';
                    $("#cartProductTableBody").append(row);
                    sumQuantity += product.quantity;
                    sumTotal += product.total;
                });
                var sum = '<tr><td></td><td></td><td></td><td></td><td>' + sumQuantity + '</td>' + '<td>' + Math.round(sumTotal * 100) / 100 + '</td><td><button class="btn btn-success buy" data-action="buy">Buy</button></td></tr>'
                $("#cartProductTableBody").append(sum);
            })
            .catch(error => {
                $("#cart").text("Cart(0)")
                console.error('Error:', error);
            });
    }

    $("#cartProductTableBody").on("click", ".addToCart, .removeFromCart, .buy", function () {
        var productId = $(this).closest("tr").data("product-id");
        var action = $(this).data("action");
        if (action === "buy") {
            var apiUrl = 'http://localhost:8080/order/5';
            fetch(apiUrl, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                }).then(getCart)
                .catch(error => {
                    console.error('Error:', error);
                })
        } else if (action === "add") {
            var apiUrl = 'http://localhost:8080/cart/5/' + productId;
            fetch(apiUrl, {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                }).then(getCart)
                .catch(error => {
                    console.error('Error:', error);
                })
        } else if (action === "remove") {
            var apiUrl = 'http://localhost:8080/cart/5/' + productId;
            fetch(apiUrl, {
                method: 'DELETE', headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                }).then(getCart)
                .catch(error => {
                    console.error('Error:', error);
                })
        }
    });

    getCart();
});