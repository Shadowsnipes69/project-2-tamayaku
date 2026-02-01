let hunger = 100;
let sleep = 100;
let fun = 100;

let alive = true;

// Bars
const hungerBar = document.querySelector(".hunger");
const sleepBar = document.querySelector(".sleep");
const funBar = document.querySelector(".fun");

// UI
const message = document.getElementById("message");
const foodBtn = document.getElementById("food");
const sleepBtn = document.getElementById("sleep");
const playBtn = document.getElementById("play");

// Update UI
function update() {
  hungerBar.style.width = hunger + "%";
  sleepBar.style.width = sleep + "%";
  funBar.style.width = fun + "%";

  hungerBar.classList.toggle("low", hunger < 30);
  sleepBar.classList.toggle("low", sleep < 30);
  funBar.classList.toggle("low", fun < 30);

  if (!alive) return;

  if (hunger < 30) {
    message.textContent = "Ik heb honger";
  } else if (sleep < 30) {
    message.textContent = "Ik ben moe";
  } else if (fun < 30) {
    message.textContent = "Ik wil spelen";
  } else {
    message.textContent = "Het gaat goed";
  }
}

// Buttons
foodBtn.onclick = () => {
  if (!alive) return;
  hunger = Math.min(100, hunger + 5);
  update();
};

sleepBtn.onclick = () => {
  if (!alive) return;
  sleep = Math.min(100, sleep + 5);
  update();
};

playBtn.onclick = () => {
  if (!alive) return;
  fun = Math.min(100, fun + 5);
  update();
};

// Game loop
setInterval(() => {
  if (!alive) return;

  hunger--;
  sleep--;
  fun--;

  hunger = Math.max(0, hunger);
  sleep = Math.max(0, sleep);
  fun = Math.max(0, fun);

  if (hunger <= 0 || sleep <= 0) {
    alive = false;
    message.innerText = "Rayquaza brutally died...";
    localStorage.setItem("alive", false);
    return;
  }

  update();
}, 500);

// Start
update();
