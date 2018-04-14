<%-- 
    Document   : customer
    Created on : 10 avr. 2018, 08:23:53
    Author     : corentin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
        <link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
        <link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <title>Interface client</title>
        
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function() {
                $('.edit_link').click(function(e) {
                    e.preventDefault();
                    let tds = $(this).parents('tr').find('td');
                    
                    editTd(tds[0], $(tds[3]).html());
               
                    });
                });
            
            function editTd(td, orderNum) {
                    $(td).prop('contenteditable', true);
                    $(td).focus();
                    $(td).on('focusout', function(e) {
                        $(td).prop('contenteditable', false);
                        $(td).blur();
                    })
                    $(td).on('keypress', function(e) {
                        if(e.which == 13) {
                            e.preventDefault();
                            $.post('customer?action=update_quantity', {orderNum : orderNum, newQuantity: $(td).html()})
                                    .done(function(data) {
                                        location.reload();
                                    });
                            }
                        });
            }
             
            
        </script>
    </head>

    <body class="dashboard">
        <main class="wrapper">
            <header class="header">
                <section class="header__wrapper">
                    <a class="brand" href="index.html">
                        <h1 class="brand__title">Interface client</h1>
                    </a>
                    <ul class="menu float-right">
                        <li class="menu__item">
                            <i class="fa fa-sign-out-alt"></i>
                            <a href="disconnect" class="menu__link">Déconnexion</a>
                        </li>
                    </ul>
                </section>
            </header>
            <div class="row clearfix">
                <article class="column column-75 column-offset-15 content">
                    <h1 class="content__title">Bienvenue ${ name } ! </h1>
                    <br >
                    <h2 class="content__title">
                        <i class="fas fa-shopping-basket"></i> Vos commandes
                    </h2>
                    <table>
                        <tr>
                            <th>Quantité</th>
                            <th>Nom du produit</th>       
                            <th>Date de l'achat</th>
                            <th>Numéro de commande</th>
                            <th>Prix de la commande</th>
                            <th></th>
                        </tr>
                        <c:set var="total" value="0" />
                        <c:forEach var="purchaseOrder" items="${list_purchase_order}">
                            <tr>
                                <td>${ purchaseOrder[0] }</td>
                                <td>${ purchaseOrder[1] }</td>
                                <td>${ purchaseOrder[3] }</td>
                                <td>${ purchaseOrder[4] }</td>
                                 <fmt:setLocale value = "fr_FR"/>
                                <td><fmt:formatNumber value="${purchaseOrder[2]}" /> €</td>
                                <c:set var="total" value="${total + Float.parseFloat(purchaseOrder[2]) }" scope="page"/>
                                <td><a href="#" class="edit_link"><i class="fa fa-edit"></i></a></td>
                            </tr>
                        </c:forEach> 
                        <tr style="margin-bottom: 15px">

                        </tr>
                
                        <tr>
                            <td><b>Total</b></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><b><fmt:formatNumber value="${total}" /> €</b></td>
                            <td>
                            
                  

                    </table>

                    <br>

                    <h2 class="content__title">
                        <i class="fas fa-plus"></i> Ajouter une  commande
                    </h2>
                    <div class="row">
                        <div class="column">
                            <select name="Produits" form="carform">
                                <option value="" disabled selected>Produit</option>
                                <c:forEach var="product" items="${list_products}">
                                    <option value="${product.value}">${ product.key }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="column">
                            <input type="number" placeholder="Quantité">
                        </div>
                    </div>

                    <div class="row">
                        <div class="column">
                            <p>Total de la commande : </p>
                        </div>
                        <div class="column column-75">
                            <input type="text" name="Prix de la commande" disabled />
                        </div>

                    </div>
                </article>
            </div>
        </main>
    </body>

</html>

