<%-- 
    Document   : admin
    Created on : 16 mars 2018, 09:17:49
    Author     : corentin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=7">
        <title>Interface administrateur</title>
        <link rel="stylesheet" href="css/style.css">

        <link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
        <link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
        <link rel="stylesheet" href="css/pikaday_custom.css">

        <script src="http://momentjs.com/downloads/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>

        <script src="https://unpkg.com/chart.js@2.7.2/dist/Chart.min.js"></script>
        <script>
            var tableLabels = [];
            var tableData = [];
            <c:set var="count" value="0" />
            <c:forEach items="${tBC}" var="entry">
            tableLabels[${ count }] = "${ entry.key }";
            tableData[${ count }] = ${ entry.value };
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
            var color = Chart.helpers.color;
            var barChartData = {
                labels: tableLabels,

                datasets: [{
                        label: "Chiffre d'affaires",
                        backgroundColor: color('rgb(155, 77, 202)').alpha(0.5).rgbString(),
                        borderColor: 'rgb(155, 77, 202)',
                        borderWidth: 1,
                        data: tableData
                    }]

            };

            window.onload = function () {
                var ctx = document.getElementById('canvas').getContext('2d');
                window.myBar = new Chart(ctx, {
                    type: 'bar',
                    data: barChartData,
                    options: {
                        responsive: true,

                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: "Chiffres d'affaires par zone géographique"
                        },
                        scales: {
                            xAxes: [{
                                    ticks: {
                                        autoSkip: false
                                    }
                                }],
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: false
                                    }
                                }]
                        }
                    }
                });
                
                  new Pikaday({
                    field: document.getElementById('date_debut_input'),
                    format: 'DD/MM/YYYY',

                    i18n: {
                        previousMonth: 'Mois précédent',
                        nextMonth: 'Mois suivant',
                        months: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
                        weekdays: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
                        weekdaysShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam']
                    }

                });

                new Pikaday({
                    field: document.getElementById('date_fin_input'),
                    format: 'DD/MM/YYYY',

                    i18n: {
                        previousMonth: 'Mois précédent',
                        nextMonth: 'Mois suivant',
                        months: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
                        weekdays: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
                        weekdaysShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam']
                    }

                });

            };
        </script>
    </head>

    <body class="dashboard">
        <main class="wrapper">
            <header class="header">
                <section class="header__wrapper">
                    <a class="brand" href="index.html">
                        <h1 class="brand__title">Interface administrateur</h1>
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
                <aside class="column column-25 aside">
                    <nav class="nav">
                        <div class="nav-label">
                            General
                        </div>

                        <ul class="nav-list">
                            <li>
                                <a href="admin">
                                    <i class="fa fa-tachometer-alt"></i>
                                    <span>Dashboard</span>
                                </a>
                            </li>
                        </ul>

                        <div class="nav-label">
                            Administration
                        </div>
                        <ul class="nav-list">
                            <li>
                                <a href="admin?action=turnover_by_category">
                                    <i class="fa fa-table"></i>
                                    <span>Chiffres d'affaires par catégorie d'article</span>
                                </a>
                            </li>
                            <li>
                                <a class="is-active" href="admin?action=turnover_by_state">
                                    <i class="fa fa-globe"></i>
                                    <span>Chiffres d'affaires par zone géographique</span>
                                </a>
                            </li>
                            <li>
                                <a href="admin?action=turnover_by_customer">
                                    <i class="fa fa-user"></i>
                                    <span>Chiffres d'affaires par client</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </aside>
                <article class="column column-75 column-offset-25 content">
                    <h2 class="content__title">
                        <i class="fa fa-globe"></i> Chiffres d'affaires par zone géographique
                    </h2>

                    <form style="padding: 15px; background-color: #f5f5f5"> 
                        <div class="row">
                            <input class="column column-25 date-input" style="margin-left: 17.5%" type="text" id="date_debut_input" placeholder="Date de début">
                            <input class="column column-25 date-input" type="text" id="date_fin_input" placeholder="Date de fin">
                            <input class="column column-25 date-input"type="submit" value="Valider">
                        </div>
                    </form>

                    <div class="content__wrapper">
                        <div class="row">
                            <div class="column">
                                <canvas id="canvas"></canvas>
                            </div>
                        </div>
                    </div>
                </article>
            </div>
        </main>
    </body>

</html>
