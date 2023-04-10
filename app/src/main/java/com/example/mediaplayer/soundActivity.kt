package com.example.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class soundActivity : AppCompatActivity() {
    var listitems= mutableListOf<listItem>()
    lateinit var listView:ListView
    var sound = MediaPlayer()
     lateinit var seekBar: SeekBar
    lateinit var btn_play: Button
    lateinit var btn_pause:Button
    lateinit var btn_stop:Button
    lateinit var tvTitle: TextView
    lateinit var tvCurrentTime:TextView
    lateinit var tvTotalTime:TextView
    var photos= listOf<Int>(R.drawable.bear,R.drawable.camel,R.drawable.cow,R.drawable.dog)
    var sounds= listOf<Int>(R.raw.app_src_main_res_raw_bear,R.raw.app_src_main_res_raw_camel,R.raw.app_src_main_res_raw_cow,R.raw.app_src_main_res_raw_dog)
    var titles= listOf<String>("Bear","Camel","Cow","Dog")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)


        listView = findViewById(R.id.listView2)
        seekBar = findViewById(R.id.seekbar)
        btn_play = findViewById(R.id.btn_play)
        btn_pause = findViewById(R.id.btn_pause)
        btn_stop = findViewById(R.id.btn_stop)

        tvTitle = findViewById(R.id.title)
        tvCurrentTime = findViewById(R.id.currenttime)
        tvTotalTime = findViewById(R.id.totaltime)

        for (i in 0 until photos.size) {
            listitems.add(listItem(titles.get(i), photos.get(i), sounds.get(i)))
        }


        val listAdapter = listAdapter(listitems)
        listView.adapter = listAdapter

        sound = MediaPlayer.create(this@soundActivity, sounds.get(0))

        listView.onItemClickListener =
            OnItemClickListener { adapterView, view, i, l ->
                view.isSelected = true
                sound.stop()
                sound.reset()
                sound = MediaPlayer.create(this@soundActivity, listitems[i].sound)
                tvTitle.text = listitems[i].title
                SoundTime()
            }

        btn_play.setOnClickListener {
            if (!sound.isPlaying) {
                val updateSeekBar: Thread
                updateSeekBar = object : Thread() {
                    override fun run() {
                        val SoundDuration = sound.duration
                        var currentPostion = 0
                        seekBar.max = SoundDuration
                        while (currentPostion < SoundDuration) {
                            try {
                                sleep(100)
                                currentPostion = sound.currentPosition
                                seekBar.progress = currentPostion
                            } catch (e: InterruptedException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
                sound.start()
                updateSeekBar.start()
            }
        }

        btn_stop.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.stop()
            }
        })
        btn_pause.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.pause()
            }
        })

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) sound.seekTo(i)
                SoundTime()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


    }
    fun SoundTime() {
        seekBar.setMax(sound.getDuration());
        var tim:Int = (seekBar.getMax() / 1000);
        var m :Int= tim / 60;
        var s :Int= tim % 60;
        //////
        var tim0 :Int= (seekBar.getProgress() / 1000);
        var m0:Int = tim0 / 60;
        var s0:Int = tim0 % 60;

        tvTotalTime.setText( "$s : $m" );
        tvCurrentTime.setText( "$s0 : $m0");
    }
}