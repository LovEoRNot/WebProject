var prev = document.querySelector('span[data-change=prev]');
var start = document.querySelector('span[data-change=start]');
var stop = document.querySelector('span[data-change=stop]');
var next = document.querySelector('span[data-change=next]');
var folder = document.querySelector('span[data-change=folder]');
var music = document.getElementById('music');
var panel = document.querySelector('.panel');
var album = document.querySelector('.album');
var current = document.getElementById('currentTime');
var progress = document.querySelector('progress');
var trash = document.querySelectorAll('trash')
var range = document.querySelector('input[type=range]');
var volumn = document.getElementById('volumn');
var rangeWrap = range.parentNode;
var time = 0, nowMusic = 0, totalMusic = 0;

var musics = ['林俊杰 - 我还想她.mp3', '陈一发 - 童话镇.mp3', '薛之谦 - 意外.mp3']

init();

start.addEventListener('click', startSound);              //播放音乐
stop.addEventListener('click', stopSound);                //暂停比方
music.addEventListener('timeupdate', getCurrentTime);     //更新时间戳
folder.addEventListener('click', function() {             //打开歌曲列表     
    if(panel.style.display === 'none') {
        panel.style.display = 'block';
        addClass(panel, 'fadeIn', function() {
            panel.style.background = '#fff';
        });
        removeClass(panel, 'fadeOut')
    } else {
        addClass(panel, 'fadeOut', function() {
            setTimeout(()=>{
                panel.style.background = 'rgba(0,0,0,0)';
                panel.style.display = 'none';                       
            }, 1000);
        });
        removeClass(panel, 'fadeIn');                 
    }
});
album.addEventListener('click', function(event) {         
    var target = event.target || event.srcElement;
    //选择歌曲
    if(target.nodeName.toLowerCase() === 'li') {    
        var src = './music/' + getText(target) + '.mp3';              
        addClass(target, 'active');               
        for(var i=0; i<album.children.length; i++) {
            var cur = album.children[i];
            if(cur.innerHTML === target.innerHTML) {
                nowMusic = (i + 1 > 2) ? 0 : i + 1;
                judgeClass(album.children, i, 'active');
            }
        }               
        music.src = src;
        var name = getMusicName(music.src);  
        document.querySelector('.head li marquee').innerHTML = name;                 
        time = 0;
        //music.load()
        startSound();
    }

    //删除歌曲 伪删除
    if(target.nodeName.toLowerCase() === 'i') {
        stopSound();
        album.removeChild(target.parentNode);
        music.src = '';               
        document.querySelector('.head li marquee').innerHTML = '网页版mp3播放器'; 
        current.innerHTML = '00:00';
        document.getElementById('totalTime').innerHTML = '00:00';
    }
});
//如果音乐播放则显示其播放时长
music.addEventListener('canplay', function() {
    if(time === 0) {                             
        time = parseInt(music.duration);
        document.getElementById('totalTime').innerHTML = getTime(time);
    } 
});
//音量图标显示
volumn.addEventListener('click', function() {
    if(rangeWrap.style.display === 'none') {
        rangeWrap.style.display = 'inline-block';
    } else {
        rangeWrap.style.display = 'none';
    }
});
var display = null;
rangeWrap.addEventListener('mouseover', function() {
    if(display !== null) {
        clearTimeout(display);
    }
});
rangeWrap.addEventListener('mouseout', function() {
    display = setTimeout(()=> {
        rangeWrap.style.display = 'none';
    }, 500);
});
//调整音量
range.style.background = 'linear-gradient(to right, #059CFA, white ' + range.value + '%, white)';
range.addEventListener('input', function() {   
    this.style.background = 'linear-gradient(to right, #059CFA, white ' + this.value + '%, white)';
    var val =  parseInt(this.value),
        max = parseInt(this.max);
    var vol = val / max;
    music.volume = vol;   
    
    if(val === 0) {
        removeClass(volumn, 'am-icon-volume-down');
        addClass(volumn, 'am-icon-volume-off')
    } else if(val > 60) {
        removeClass(volumn, 'am-icon-volume-down');
        addClass(volumn, 'am-icon-volume-up')
    } else {
        removeClass(volumn, 'am-icon-volume-off');
        removeClass(volumn, 'am-icon-volume-up');
        addClass(volumn, 'am-icon-volume-down')
    }
});

//下一首
next.addEventListener('click', function() {
    var src = './music/' + musics[nowMusic]; 
    nowMusic = parseInt((nowMusic + 1) % totalMusic);   
    music.src = src;
    var name = getMusicName(music.src);  
    document.querySelector('.head li marquee').innerHTML = name;                 
    time = 0;
    startSound();
})
//上一首
prev.addEventListener('click', function() {
    var src = './music/' + musics[nowMusic]; 
    nowMusic = parseInt((nowMusic - 1) % totalMusic);
    music.src = src;
    var name = getMusicName(music.src);  
    document.querySelector('.head li marquee').innerHTML = name;                 
    time = 0;
    startSound();
})

//初始化信息
function init() {
    totalMusic = musics.length;
    musics.forEach(function(item) {
        var name = getMusicName(item);

        var node = document.createElement('li');
        node.innerHTML = name;
        var trash = document.createElement('i');
        trash.className = 'am-icon-trash trash'; 
        trash.style.display = 'none';            
        node.appendChild(trash);
        album.appendChild(node);         
    });
    music.volume = range.value / range.max; 
}

function startSound() {
    stop.style.display = 'inline-block';
    start.style.display = 'none';         
    if(music.getAttribute('src') !== '') {
        music.play();                 
    }                         
}  

function stopSound() {
    start.style.display = 'inline-block';
    stop.style.display = 'none';
    music.pause()         
}  

function getCurrentTime() {
    var currentTime = parseInt(music.currentTime);
    progress.value = parseInt(currentTime * 100 / time);
    current.innerHTML = getTime(currentTime);
}


//以下都为工具方法
/*
    eles: 元素集合
    current： 当前元素的索引
    className：类名
*/
function judgeClass(eles, current, className) {
    for(var i=0; i<eles.length; i++) {
        if(i !== current) {
            removeClass(eles[i], className);
            eles[i].children[0].style.display = 'none';   
        } else {
            eles[i].children[0].style.display = 'inline-block';
        }
    }
}
//获取元素e中的文本内容
function getText(e) {
    var t = '';
    e = e.childNodes||e;           
    for(var j=0; j < e.length; j++){
        t += e[j].nodeType !== 1 ?
            e[j].nodeValue : ''; //如果''替换成getText(e[j].childNodes)可以获得该元素下包括其子节点的文本
    }
    return t;
}
function getMusicName(src) {
    var name = (src.slice(src.lastIndexOf('\/') + 1, src.length));
    name = decodeURIComponent(name.slice(0, name.length-4));           
    return name;          
}
function getTime(time) {
    var minute = parseInt(time / 60);
    var seconds = parseInt(time - minute * 60);
    minute = minute > 10 ? minute : '0' + minute;
    seconds = seconds > 10 ? seconds : '0' + seconds;

    return minute + ':' + seconds;
}

function hasClass(ele, className) {
    return !!ele.className.match(new RegExp( "(\\s|^)" + className + "(\\s|$)"));
}
function addClass( elements, className, callback){ 
    if( !hasClass( elements, className ) ){ 
        elements.className += " " + className; 
    }; 
    if(callback) {
        callback();
    }           
}; 
function removeClass( elements, className, callback){ 
    if( hasClass( elements, className ) ){ 
        elements.className = elements.className.replace( new RegExp( "(\\s|^)" + className + "(\\s|$)" )," " );
    }
    if(callback) {
        callback();
    }
}; 

