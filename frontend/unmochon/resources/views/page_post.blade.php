@extends('layouts.app')

@section('content')
    <div class="container">
        <form action="/page" method="POST">
            <button type="submit">Submit</button>
        </form>
    </div>
@endsection
