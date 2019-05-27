package app.bxvip.com.myphone.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.util.LyricLoader
import app.bxvip.com.myphone.util.LyricUtil
import com.itheima.player.model.LyriBean
import kotlinx.android.synthetic.main.activity_music_player_bottom.view.*
import org.jetbrains.anko.doAsync
import java.time.Duration

class LyricView: View {
    val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG)} //通过惰性加载没有锯齿的paint
    val list  by lazy { ArrayList<LyriBean>() }
    var ViewW:Int = 0
    var ViewH:Int = 0
    var bigSize = 0f
    var smallSize = 0f
    var white = 0
    var green =0
    var centerLine = 0
    var lineHeight = 0
    var duration = 0
    var progress = 0
    var updateByprogress = true //指定是否可以通过进度来更新
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        bigSize = resources.getDimension(R.dimen.bigSize)
        smallSize = resources.getDimension(R.dimen.smallSize)
        white = resources.getColor(R.color.white)
        green = resources.getColor(R.color.green)
        lineHeight = resources.getDimensionPixelOffset(R.dimen.lineHeight)
        paint.textAlign = Paint.Align.CENTER
        //添加多行文本信息
        for (i  in 0 until 50){
            list.add(LyriBean(2000*i,"正在播放第${i}歌词"))
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (list.size==0){
            //绘制文本
            drawSingleLine(canvas)
        }else{
            drawMutieLine(canvas)
        }


    }
    var offsetY = 0f
    private fun drawMutieLine(canvas: Canvas?) {
        if (updateByprogress) {
            //求居中行的偏移量
            var lineTime = 0
            //行可用时间  这里是最后一行的行可用时间
            if (centerLine == list.size - 1) {
                //duration-最后一行的开始时间
                lineTime = duration - list.get(centerLine).startTime
            } else {
                //执行到这里说明是其他行居中
                val centerS = list.get(centerLine).startTime
                val nextS = list.get(centerLine + 1).startTime
                //行可用时间
                lineTime = nextS - centerS
            }
            //偏移时间
            val offsetTime = progress - list.get(centerLine).startTime
            val offsetPresent = offsetTime / (lineTime.toFloat())
            offsetY = offsetPresent * lineHeight
        }
        //获得中间文本的内容
        val centertext = list.get(centerLine).content
        val bonds = Rect()
        paint.getTextBounds(centertext,0, centertext.length,bonds)
        //获取中间文本信息的高度，因为当前的View是居中的，所以绘制是从中间开始的
        val textH = bonds.height()
        //获取中心X坐标
        val centerY = ViewH /2+textH/2 -offsetY
        //遍历集合中的相关数据，分别绘制
        for ((index, value) in list.withIndex()){
            if(index==centerLine){
                //绘制居中行
                paint.color = green
                paint.textSize = bigSize
            }else{
                paint.color = white
                paint.textSize = smallSize
            }
            val curX = ViewW/2
            val curY = centerY+(index-centerLine)*lineHeight
            //超出边界处理
            //超出上边界
            if(curY<0) continue  //超出上限 重新循环
            if(curY>ViewH+lineHeight) break //超出下限直接跳出循环

            val curText = list.get(index).content
            canvas?.drawText(curText, curX.toFloat(), curY.toFloat(), paint)
        }
    }

    /**
     * 绘制单行居中文本信息
     */
    private fun drawSingleLine(canvas: Canvas?) {
        paint.textSize = bigSize
        paint.color = green
        val text = "正在加载歌词"
        //求文本的高度和宽度
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)

        val textWidth = bounds.width()
        val textHeight = bounds.height()
        //        val x = ViewW/2 -textWidth/2
        val y = ViewH / 2 - textHeight / 2
        //绘制内容
        canvas?.drawText(text, (ViewW / 2).toFloat(), y.toFloat(), paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        ViewW = w
        ViewH = h
    }

    /**
     * 传递当前的播放进度，以实现歌词的播放
     */
    fun updateProgress(progress:Int){
        if (list.size==0) return
        if (!updateByprogress) return
        this.progress = progress
        if (progress>=list.get(list.size-1).startTime){
            //最后一行居中
            centerLine = list.size -1
        }else{
            //其他行居中显示
            for(index in 0 until list.size -1){
                //progress>= 当前行的开始时间<下一行开始时间
                val curStartTime = list.get(index).startTime
                val nextStartTiem = list.get(index+1).startTime
                if (progress>=curStartTime&&progress<nextStartTiem){
                    centerLine = index
                    break
                }
            }
        }
        //找到局中行后再重新绘制
        invalidate() // onDraw
//        postInvalidate() // onDraw 可以在子线程中刷新
//        requestLayout() //view布局参数方法改变
    }

    /**
     * 设置当前播放歌曲的总时长
     */
    fun setSongDuration(duration:Int){
        this.duration = duration
    }

    var downY = 0f
    var markY= 0f
    /**
     * 1, 手指按下的时候停止进度更新歌词的操作
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let{
            when (event.action){
                MotionEvent.ACTION_DOWN->{
                    updateByprogress = false
                    downY = event.y //手指按下的Y值
                    markY = this.offsetY
                }
                MotionEvent.ACTION_UP->{
                    updateByprogress = true
                }
                MotionEvent.ACTION_MOVE->{
                    //当前的Y = event.y
                    val endy = event.y
                    val offY = downY-endy
                    this.offsetY = offY+markY
                    if(Math.abs(this.offsetY)>=lineHeight){
                        //求居中行行号的偏移
                        val offsetLine = (this.offsetY/lineHeight).toInt()
                        centerLine += offsetLine
                        //对居中行做便捷处理
                        if (centerLine<0) centerLine = 0
                        else if(centerLine>list.size-1) centerLine = list.size-1
                        this.downY = endy
                        this.offsetY = this.offsetY%lineHeight
                        markY = this.offsetY
                        //更新播放进度
                        listener?.let{
                            it(list.get(centerLine).startTime)
                        }
//                        listener?.invoke(list.get(centerLine).startTime)
                    }

                    //重新绘制
                    invalidate()
                }
            }
        }
        //消费这个事件
        return true
    }

    /**
     * 设置歌曲播放名称
     * 解析歌词文件
     */
    fun setSongName(name:String){
        doAsync {
            this@LyricView.list.clear()
            this@LyricView.list.addAll(LyricUtil.parseLyric(LyricLoader.loadLyricFile(name)))
        }
    }
    //进度的回调
    private var listener:((progress:Int)->Unit)? = null
    fun setProgressListener(listener : (progress:Int)->Unit){
        this.listener = listener
    }
}


