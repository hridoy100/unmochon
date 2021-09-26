<?php

use App\Http\Controllers\GraphController;
use App\Http\Controllers\ImageDataController;
use App\Http\Controllers\Auth\LoginController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UnmochonJavaController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::post('/image', [ImageDataController::class, 'index']);

Route::get('/login/facebook', [LoginController::class, 'redirectToFacebookProvider']);

Route::get('login/facebook/callback', [LoginController::class ,'handleProviderFacebookCallback']);

Auth::routes(['register' => false]);

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');


Route::get('/db', [\App\Http\Controllers\DatabaseController::class, 'index']);


Route::group(['middleware' => [
    'auth'
]], function(){
    Route::get('/user', [GraphController::class, 'retrieveUserProfile']);
    Route::get('/page-post', function (){
        return view('page_post');
    });
    Route::post('/user', [GraphController::class, 'publishToProfile']);
});
Route::post('/page', [GraphController::class, 'publishToPage']);

Route::post('/upload_pic/{userid}', [UnmochonJavaController::class, 'uploadPic']);
Route::post('/upload_details', [UnmochonJavaController::class, 'uploadDetails']);
