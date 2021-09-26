@extends('layouts.master')

@section('content')

    <div class="page-header header-filter clear-filter sepia-filter" data-parallax="true" style="background-image: url({{asset('assets/img/unmochon.JPG')}}); height: 40rem">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ml-auto mr-auto">
                    <div class="brand">
                        <h1 id="app"></h1>
                        <h4 class="title" style="padding-bottom: 6rem" >Women in the global south often seek justice to their online harassment
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
                    <div class="col-xl-4 col-md-4">
                        <div class="causes_info">
                            <div class="section_title">
                                <span class="text-info" style="font-weight: normal">Meet Bangladeshi Researcher</span>
                                <h3 class="title" style="color: whitesmoke">
                                    DR. ISHTIAQUE AHMED
                                </h3>
                            </div>
                            <h4 class="" style="color: lightgray">
                                Assistant Professor of Computer Science at the University of Toronto.
                            </h4>
                        </div>
                    </div>

                    <div class="col-xl-8 col-md-8">
                        <img src="{{asset('assets/img/ishtiaque_speech.jpg')}}" alt="" height="500" style="border-radius: 300px;">
                    </div>
                </div>
            </div>
        </div>
        <!-- causes_area_end -->
        <hr>

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
