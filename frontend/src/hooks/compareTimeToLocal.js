const compareTimeToLocal = (time) => {
  const timeNow = new Date();
  const timeNowLocal = new Date(
    timeNow.getFullYear(),
    timeNow.getMonth(),
    timeNow.getDate(),
    timeNow.getHours(),
    timeNow.getMinutes(),
    timeNow.getSeconds()
  );
  const timeLocal = new Date(time);
  const timeDifference = timeNowLocal.getTime() - timeLocal.getTime();
  if (timeDifference > 72 * 60 * 60 * 1000) {
    console.log("NO HAY TURNOS EN LAS PROXIMAS 72 HORAS");;
  }
  return timeDifference;
};
export default compareTimeToLocal;
