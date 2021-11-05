@extends('layouts.master')

@section('content')

    <div class="page-header header-filter clear-filter sepia-filter" data-parallax="true" style=" height: 25rem">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ml-auto mr-auto">
                    <div class="brand">
                        <h2 class="title">VICTIM SUPPORT</h2>
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
                                <h3 class="" style="color: whitesmoke; font-weight: bold">Bangladesh Police Cyber<br>
                                    Security Team contact</h3>
                                <div>
                                    <a href="https://www.police.gov.bd/en/unitContact" class="btn btn-danger">Contact with police unit</a>
                                    <a href="https://www.police.gov.bd/en/contacts_of_bangladesh_police_-pdf" class="btn btn-info">Contacts of Bangladesh Police (PDF Format)</a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-xl-6 col-md-6">
                        <div class="causes_info ">
                            <div class="section_title">
                                <h3 class="" style="color: whitesmoke; font-weight: bold">Our support team</h3>
                                <div>
                                    <a href="mailto:unmochon@gmail.com" class="text-info">
                                        <div style="display: flex; align-items: center">
                                            <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/letter.png')}}" alt="" style="width: 100px; background-position: left"> unmochon@gmail.com
                                        </div>
                                    </a>
                                </div>
                            </div>

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
