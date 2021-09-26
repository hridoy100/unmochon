<?php

namespace App\Console\Commands;

use App\Http\Controllers\GraphController;
use http\Client\Request;
use Illuminate\Console\Command;
use Illuminate\Routing\Route;
use Illuminate\Support\Facades\Http;

class PostToFB extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'post:tofb';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Command description';

    /**
     * Create a new command instance.
     *
     * @return void
     */
    public function __construct()
    {
        parent::__construct();
    }

    /**
     * Execute the console command.
     *
     * @return int
     */
    public function handle()
    {
//        Http::post("http://unmochon.org/page");
//        $response = Route::dispatch($request);
        Http::post('http://unmochon.org/page');
        return 0;
    }
}
