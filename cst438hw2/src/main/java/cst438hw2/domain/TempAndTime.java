package cst438hw2.domain;

public class TempAndTime {
	public double temp;
	public long time;
	public int timezone;
	
	public TempAndTime(double temp, long time, int timezone){
		this.temp = Math.round(((temp - 273.15) * 9.0/5.0 + 32.0)*100.0)/100.0;
		//  this.time = time;
		this.timezone = timezone;
		this.time = (time + timezone) * 1000;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	
 }
