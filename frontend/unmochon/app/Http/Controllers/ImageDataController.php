<?php

namespace App\Http\Controllers;

use App\Models\ImageData;
use Illuminate\Http\Request;

class ImageDataController extends Controller
{
    public function index()
    {
        if(request()->hasFile('image_data')){
            $file = request()->file('image_data');
            $extension = $file->getClientOriginalExtension();
            $filename = "image".time() . '.' . $extension;
            $file->move('img/', $filename);
            ImageData::create([
                "image_url" => 'img/'.$filename,
            ]);
        }
        return response('Success', 200)
            ->header('Content-Type', 'text/plain');
    }
}
