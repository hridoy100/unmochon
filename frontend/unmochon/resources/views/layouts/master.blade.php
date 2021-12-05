<!--
=========================================================
Material Kit - v2.0.7
=========================================================

Product Page: https://www.creative-tim.com/product/material-kit
Copyright 2020 Creative Tim (https://www.creative-tim.com/)

Coded by Creative Tim

=========================================================

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="{{asset('img/apple-icon.png')}}">
    <link rel="icon" type="image/png" href="{{asset('img/favicon.png')}}">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Unmochon
    </title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
    <link rel="stylesheet" href="{{asset('css/app.css')}}">

    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <!-- CSS Files -->
    <link href="{{asset('assets/css/material-kit.css?v=2.0.7')}}" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="{{asset('assets/demo/demo.css')}}" rel="stylesheet" />
    {{--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--}}
{{--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">--}}
{{--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/font-awesome.min.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">--}}
{{--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.min.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">--}}

    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"  type='text/css'>
    <link rel="stylesheet" href="{{asset('css/style.css')}}">
    <link rel="stylesheet" href="css/team.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:900" rel="stylesheet">

    <!-- Global site tag (gtag.js) - Google Analytics -->
{{--    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-163972268-1"></script>--}}
{{--    <script>--}}
{{--        window.dataLayer = window.dataLayer || [];--}}
{{--        function gtag(){dataLayer.push(arguments);}--}}
{{--        gtag('js', new Date());--}}

{{--        gtag('config', 'UA-163972268-1');--}}
{{--    </script>--}}

{{--    {!! SEOMeta::generate() !!}--}}
{{--    {!! OpenGraph::generate() !!}--}}
{{--    {!! Twitter::generate() !!}--}}
{{--    {!! JsonLd::generate() !!}--}}

</head>

<body class="index-page sidebar-collapse bg-white">
<nav class="navbar bg-success navbar-color-on-scroll fixed-top navbar-expand-lg" color-on-scroll="100" id="sectionsNav" style="font-weight: 600;">
    <div class="container">
        <div class="navbar-translate">
            <a class="navbar-brand" href="/" style="font-size: larger">
{{--                <img src="{{asset('img/shomonnoi-logo.png')}}" alt="" height="35px">--}}
                Unmochon
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/" onclick="scrollToDownload()">
                        <div style="display: flex; align-items: center">
                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/home_normal.png')}}" alt="" style="width: 20px; background-position: left"> Home
                        </div>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/db" class="nav-link">
                        <div style="display: flex; align-items: center">
                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/database_white.png')}}" alt="" style="width: 20px; background-position: left"> Database
                        </div>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/download" class="nav-link">
                        <div style="display: flex; align-items: center">
                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/cloud-download.png')}}" alt="" style="width: 20px; background-position: left"> Download
                        </div>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/victim-support" class="nav-link">
                        <div style="display: flex; align-items: center">
                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/support_white.png')}}" alt="" style="width: 20px; background-position: left"> Victim Support
                        </div>
                    </a>
                </li>

{{--                <li class="nav-item">--}}
{{--                    <a href="/contact" class="nav-link">--}}
{{--                        <div style="display: flex; align-items: center">--}}
{{--                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/contact.png')}}" alt="" style="width: 20px; background-position: left"> Contact--}}
{{--                        </div>--}}
{{--                    </a>--}}
{{--                </li>--}}

                <li class="nav-item">
                    <a class="nav-link" href="/tutorial">
                        <div style="display: flex; align-items: center">
                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/tutorial.png')}}" alt="" style="width: 20px; background-position: left"> Tutorial
                        </div>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/team">
                        <div style="display: flex; align-items: center">
                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/teamwork_white.png')}}" alt="" style="width: 20px; background-position: left"> Unmochon Team
                        </div>
                    </a>
                </li>

            </ul>
        </div>
    </div>
</nav>

@yield('content')


<footer class="footer bg-white" data-background-color="black" style="color: whitesmoke; position: fixed; width: 100%; display: flex; justify-content: center; bottom: 5px">

    <div style="position: absolute; right: 50px; top: -120px;">
        <img src="{{asset('assets/img/ict_div.png')}}" alt="" style="width: 150px">
    </div>

    <div class="container">
        <div class="row">
            <div class="col-10">
                <div style="position:absolute; bottom: 0">
                    <h5 style="display: flex; color: black">Project Unmochon is funded by Information and Communication Technology  Division (ICTD), Bangladesh.</h5>
                </div>
            </div>
            <div class="col-2">
            </div>

            {{--            <div class="copyright float-right" style="font-size: small; font-weight: 400; color: whitesmoke">--}}
{{--                Copyright &copy;--}}
{{--                <script>--}}
{{--                    document.write(new Date().getFullYear())--}}
{{--                </script>,  All rights reserved | by--}}
{{--                <a href="/" target="_blank" class="text-success">unmochon.org</a>--}}
{{--                --}}{{--            Coded by <a href="https://facebook.com/hridoy100" target="_blank">Raihanul Alam Hridoy</a>.--}}
{{--            </div>--}}
        </div>
{{--        <div><span class="btn btn-outline-primary align-middle">VISITORS: {{$analyticsData}}</span></div>--}}

    </div>
</footer>
<!--   Core JS Files   -->
<script src="{{asset('assets/js/core/jquery.min.js')}}" type="text/javascript"></script>
<script src="{{asset('assets/js/core/popper.min.js')}}" type="text/javascript"></script>
<script src="{{asset('assets/js/core/bootstrap-material-design.min.js')}}" type="text/javascript"></script>
<script src="{{asset('assets/js/plugins/moment.min.js')}}"></script>
<!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
<script src="{{asset('assets/js/plugins/bootstrap-datetimepicker.js')}}" type="text/javascript"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="{{asset('assets/js/plugins/nouislider.min.js')}}" type="text/javascript"></script>
<!--  Google Maps Plugin    -->
<!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
<script src="{{asset('assets/js/material-kit.js?v=2.0.7')}}" type="text/javascript"></script>
{{--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>--}}
{{--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>--}}
<script src="https://unpkg.com/typewriter-effect@latest/dist/core.js"></script>

<script>
    $(document).ready(function() {
        //init DateTimePickers
        materialKit.initFormExtendedDatetimepickers();

        // Sliders Init
        materialKit.initSliders();
    });


    function scrollToDownload() {
        if ($('.section-download').length != 0) {
            $("html, body").animate({
                scrollTop: $('.section-download').offset().top
            }, 1000);
        }
    }
</script>

<script src="{{asset('js/main.js')}}"></script>
</body>

</html>
