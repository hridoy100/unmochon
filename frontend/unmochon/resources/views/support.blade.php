@extends('layouts.master')

@section('content')

    <div class="page-header" data-parallax="true" style="height: 85vh">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ml-auto mr-auto">
                    <div class="brand text-dark">
                        <h2 class="title text-dark">VICTIM SUPPORT</h2>
                    </div>
                    <hr class="pb-4">

                    <div class="pt-5">
                        <ul class="text-dark" style="font-size: 22px;">
                            <li>
                                <a class="text-info" href="https://www.police.gov.bd/en/unitContact">Bangladesh Police Cyber Security Team contact</a>
                            </li>
                            <li class="pt-4">
                                <a class="text-info" href="https://www.police.gov.bd/en/contacts_of_bangladesh_police_-pdf">Contacts of Bangladesh Police (PDF Format)</a>
                            </li>
                            <li class="pt-4">
                                <a class="text-info" href="mailto:unmochon@gmail.com">
                                    <div style="display: flex; align-items: center">
                                        <img class="ml--1 pl-0 mr-2" src="{{asset('assets/img/letter.png')}}" alt=""
                                             style="width: 30px; background-position: left"> unmochon.contact@gmail.com
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
