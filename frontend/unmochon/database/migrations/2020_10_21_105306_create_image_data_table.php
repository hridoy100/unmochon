<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateImageDataTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('image_data', function (Blueprint $table) {
            $table->id();
            $table->string("userid");
            $table->text("image_url");
            $table->string("encryption_key")->nullable();
            $table->integer("is_posted_to_fb")->default(0);
            $table->timestamp("posted_at")->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('image_data');
    }
}
