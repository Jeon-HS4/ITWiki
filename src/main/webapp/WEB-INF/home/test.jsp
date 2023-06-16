<!DOCTYPE html>
<html>
<head>
    <style>
        .box {
            width: 100px;
            height: 100px;
            background-color: red;
            position: relative;
            animation-name: moveRight;
            animation-duration: 10s;
            animation-fill-mode: forwards;
            animation-play-state: paused;
        }

        @keyframes moveRight {
            from {
                left: 0;
            }
            to {
                left: 300px;
            }
        }
    </style>
</head>
<body>
<div class="box"></div>
<button onclick="startAnimation()">버튼 1</button>
<button onclick="pauseAnimation()">버튼 2</button>

<script>
    var boxElement = document.querySelector('.box');
    var animationState = 'paused';

    function startAnimation() {
        boxElement.style.animationPlayState = 'running';
        animationState = 'running';
    }

    function pauseAnimation() {
        if (animationState === 'running') {
            boxElement.style.animationPlayState = 'paused';
            animationState = 'paused';
        }
    }
</script>
</body>
</html>
