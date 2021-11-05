@extends('layouts.master')

@section('content')

    <div class="page-header header-filter clear-filter sepia-filter" data-parallax="true" style=" height: 20rem">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ml-auto mr-auto">
                    <div class="brand">
                        <h2 class="title">MEET THE TEAM</h2>
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
                                <span class="text-info" style="font-weight: normal">Project Lead</span>
                                <h3 class="" style="color: whitesmoke; font-weight: bold">
                                    Shaid Hasan
                                </h3>
                            </div>
                            <img src="{{asset('assets/img/shaid_hasan.jpg')}}" alt="" height="350">
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-4">
                        <div class="causes_info">
                            <div class="section_title">
                                <span class="text-info" style="font-weight: normal">Project Developer</span>
                                <h3 class="" style="color: whitesmoke; font-weight: bold">
                                    Raihanul Alam Hridoy
                                </h3>
                            </div>
                            <img src="{{asset('assets/img/hridoy.jpg')}}" alt="" height="350" width="350">
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-4">
                        <div class="causes_info">
                            <div class="section_title">
                                <span class="text-info" style="font-weight: normal">Main Author</span>
                                <h3 class="" style="color: whitesmoke; font-weight: bold">
                                    Sharifa Sultana Ela
                                </h3>
                            </div>
                            <img src="{{asset('assets/img/ela_apu.jpg')}}" alt="" height="350">
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
