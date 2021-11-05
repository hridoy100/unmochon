@extends('layouts.master')

@section('content')

    <div class="page-header header-filter clear-filter sepia-filter" data-parallax="true" style=" height: 40rem">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ml-auto mr-auto">
                    <div class="brand">
                        <h2 class="title" id="app"></h2>
                        <h4 class="" style="padding-top: 20px; padding-bottom: 6rem" >Women in the global south often seek justice to their online harassment
                            <br> through unveiling the harassers and the screenshots of their sent harassment texts and visual contents
                            <br>before the relevant authorities. Building on the ‘shame-based model’ of gender justice, we designed
                            <br>‘Unmochon’, a tool that captures authentic evidence and shares with victims’ intended group.
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main main-raised bg-dark">
        <div class="section section-basic">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-6 col-md-6">
                        <div class="causes_info">
                            <div class="section_title">
                                <span class="text-info" style="font-weight: normal">Online Harassment</span>
                                <h3 class="" style="color: whitesmoke; font-weight: bold">
                                    You’re Not Powerless in the Face of Online Harassment
                                </h3>
                            </div>
                            <h4 class="" style="color: lightgray">
                                Online abuse is defined as the “repeated or severe targeting online of an individual or group through harmful behavior.” Common tactics — albeit ever-evolving and
                                often overlapping — include: hateful speech, sexual harassment, threats of physical and sexual violence, impersonation, doxing, nonconsensual pornography, message bombing, and many more.
                                If you’re being critiqued or insulted, you can choose to refute it or let it go. But if you’re being abused, naming what you’re experiencing not only signals that it’s a tangible problem,
                                but can also help you communicate with allies, employers, and law enforcement. Unmochon is a tool that helps you overcome these obstacles.
                            </h4>
                        </div>
                    </div>
                    <div class="col-xl-6 col-md-6">
                        <img src="{{asset('assets/img/harassment2.png')}}" alt="" height="350" style="border-radius: 100px;">
                    </div>
                </div>
            </div>
        </div>
        <!-- causes_area_end -->
        <hr>

        <div class="section section-basic">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-4 col-md-4">
                        <img src="{{asset('assets/img/harassment1.jpg')}}" alt="" height="350" style="border-radius: 100px;">
                    </div>
                    <div class="col-xl-2 col-md-2">

                    </div>
                    <div class="col-xl-6 col-md-6">
                        <div class="causes_info">
                            <div class="section_title">
                                <span class="text-info" style="font-weight: normal">Sexual Harassment</span>
                                <h3 class="" style="color: whitesmoke; font-weight: bold">
                                    Combating Sexual Harassment
                                </h3>
                            </div>
                            <h4 class="" style="color: lightgray">
                                Women in the global south often seek justice to their online harassment through unveiling the harassers and the screenshots of their sent harassment texts and visual contents before the relevant authorities.
                                Nevertheless, such evidence is often challenged for their authenticity. Sexual harassment and bullying can happen in person or online. But no matter where they happen, sexual harassment and bullying are not OK.
                                There is no excuse for behaviors like these. And they are not the fault of the person who is being harassed or bullied.
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- The Modal -->
        <div id="myModal" class="modal">

            <!-- The Close Button -->
            <span class="close">&times;</span>

            <!-- Modal Content (The Image) -->
            <img class="modal-content" id="img01">

            <!-- Modal Caption (Image Text) -->
            <div id="caption"></div>
        </div>

    </div>
@endsection
