package com.xianqin.common;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.xianqin.utils.StringUtils;


public class DateTime extends Date implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int YEAR_TO_YEAR = 11;
	public static final int YEAR_TO_MONTH = 12;
	public static final int YEAR_TO_DAY = 13;
	public static final int YEAR_TO_HOUR = 14;
	public static final int YEAR_TO_MINUTE = 15;
	public static final int YEAR_TO_SECOND = 16;
	public static final int YEAR_TO_MILLISECOND = 17;
	public static final int MONTH_TO_MONTH = 22;
	public static final int MONTH_TO_DAY = 23;
	public static final int MONTH_TO_HOUR = 24;
	public static final int MONTH_TO_MINUTE = 25;
	public static final int MONTH_TO_SECOND = 26;
	public static final int MONTH_TO_MILLISECOND = 27;
	public static final int DAY_TO_DAY = 33;
	public static final int DAY_TO_HOUR = 34;
	public static final int DAY_TO_MINUTE = 35;
	public static final int DAY_TO_SECOND = 36;
	public static final int DAY_TO_MILLISECOND = 37;
	public static final int HOUR_TO_HOUR = 44;
	public static final int HOUR_TO_MINUTE = 45;
	public static final int HOUR_TO_SECOND = 46;
	public static final int HOUR_TO_MILLISECOND = 47;
	public static final int MINUTE_TO_MINUTE = 55;
	public static final int MINUTE_TO_SECOND = 56;
	public static final int MINUTE_TO_MILLISECOND = 57;
	public static final int SECOND_TO_SECOND = 66;
	public static final int SECOND_TO_MILLISECOND = 67;
	public static final int MILLISECOND_TO_MILLISECOND = 77;
	private static String delimiter = "-";
	private int type = 0;
	private boolean empty = true;
	private static final long MILLS_ONE_SECOND = 1000L;
	private static final long MILLS_ONE_MINUTE = 60000L;
	private static final long MILLS_ONE_HOUR = 3600000L;
	private static final long MILLS_ONE_DAY = 86400000L;

	public DateTime() {
		this((Date) null);
	}

	public DateTime(Date date) {
		this(date, 13);
	}

	public DateTime(String dateString) {
		this(dateString, 13);
	}

	public DateTime(String dateString, int type) 
	{
		setTime(dateString, type);
	}

	private void setTime(String dateTimeString, int type) {
		if ((dateTimeString == null) || (dateTimeString.trim().length() == 0)) {
			this.empty = true;
			return;
		}
		try {
			if ((dateTimeString.length() >= 8)
					&& (dateTimeString.indexOf('-') == -1)
					&& (dateTimeString.indexOf('/') == -1)) {
				String year = dateTimeString.substring(0, 4);
				String month = dateTimeString.substring(4, 6);
				String dayEtc = dateTimeString.substring(6, dateTimeString
						.length());
				dateTimeString = year + "-" + month + "-" + dayEtc;
			}
			dateTimeString = correct(dateTimeString);
			SimpleDateFormat dateFormat = getDateFormat(type);
			Date date = dateFormat.parse(dateTimeString);
			setTime(date.getTime());
			this.empty = false;
		} catch (ParseException e) {
			this.empty = true;
			throw new IllegalArgumentException("unable to parse "
					+ dateTimeString);
		}
		this.type = type;
		check(this, type);
	}

	private void setTime(Date date, int type) {
		if (date == null) {
			this.empty = true;
			return;
		}
		setTime(getDateFormat(type).format(date), type);
	}

	public DateTime(Date date, int type) {
		setTime(date, type);
	}

	public DateTime(DateTime dateTime, int type) {
		setTime(dateTime, type);
	}

	public int getYear() {
		check(this, 11);
		return Integer.parseInt(getDateFormat(11).format(this));
	}

	public int getMonth() {
		check(this, 22);
		return Integer.parseInt(getDateFormat(22).format(this));
	}

	public int getDay() {
		check(this, 33);
		return Integer.parseInt(getDateFormat(33).format(this));
	}

	public int getHour() {
		check(this, 44);
		return Integer.parseInt(getDateFormat(44).format(this));
	}

	public int getMinute() {
		check(this, 55);
		return Integer.parseInt(getDateFormat(55).format(this));
	}

	public int getSecond() {
		check(this, 66);
		return Integer.parseInt(getDateFormat(66).format(this));
	}

	public static void setDateDelimiter(String delimiter) {
		delimiter = delimiter;
	}

	public static String getDateDelimiter() {
		return delimiter;
	}

	private static SimpleDateFormat getDateFormat(int type) {
		String pattern = "";
		switch (type) {
		case 11:
			pattern = "yyyy";
			break;
		case 12:
			pattern = "yyyy" + delimiter + "MM";
			break;
		case 13:
			pattern = "yyyy" + delimiter + "MM" + delimiter + "dd";
			break;
		case 14:
			pattern = "yyyy" + delimiter + "MM" + delimiter + "dd HH";
			break;
		case 15:
			pattern = "yyyy" + delimiter + "MM" + delimiter + "dd HH:mm";
			break;
		case 16:
			pattern = "yyyy" + delimiter + "MM" + delimiter + "dd HH:mm:ss";
			break;
		case 17:
			pattern = "yyyy" + delimiter + "MM" + delimiter + "dd HH:mm:ss.SSS";
			break;
		case 22:
			pattern = "MM";
			break;
		case 23:
			pattern = "MM" + delimiter + "dd";
			break;
		case 24:
			pattern = "MM" + delimiter + "dd HH";
			break;
		case 25:
			pattern = "MM" + delimiter + "dd HH:mm";
			break;
		case 26:
			pattern = "MM" + delimiter + "dd HH:mm:ss";
			break;
		case 27:
			pattern = "MM" + delimiter + "dd HH:mm:ss.SSS";
			break;
		case 33:
			pattern = "dd";
			break;
		case 34:
			pattern = "dd HH";
			break;
		case 35:
			pattern = "dd HH:mm";
			break;
		case 36:
			pattern = "dd HH:mm:ss";
			break;
		case 37:
			pattern = "dd HH:mm:ss.SSS";
			break;
		case 44:
			pattern = "HH";
			break;
		case 45:
			pattern = "HH:mm";
			break;
		case 46:
			pattern = "HH:mm:ss";
			break;
		case 47:
			pattern = "HH:mm:ss.SSS";
			break;
		case 55:
			pattern = "mm";
			break;
		case 56:
			pattern = "mm:ss";
			break;
		case 57:
			pattern = "mm:ss.SSS";
			break;
		case 66:
			pattern = "ss";
			break;
		case 67:
			pattern = "ss.SSS";
			break;
		case 77:
			pattern = "SSS";
			break;
		case 18:
		case 19:
		case 20:
		case 21:
		case 28:
		case 29:
		case 30:
		case 31:
		case 32:
		case 38:
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:
		case 48:
		case 49:
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 58:
		case 59:
		case 60:
		case 61:
		case 62:
		case 63:
		case 64:
		case 65:
		case 68:
		case 69:
		case 70:
		case 71:
		case 72:
		case 73:
		case 74:
		case 75:
		case 76:
		default:
			throw new IllegalArgumentException(type + " is not support");
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter;
	}

	public String toString() {
		if (this.empty == true) {
			return "";
		}
		SimpleDateFormat dateFormat = getDateFormat(this.type);
		return dateFormat.format(this);
	}

	public String toString(int type) {
		if (this.empty == true) {
			return "";
		}

		SimpleDateFormat dateFormat = getDateFormat(type);
		return dateFormat.format(this);
	}

	public static DateTime current() {
		SimpleDateFormat dateFormat = getDateFormat(17);
		return new DateTime(dateFormat.format(new Date()), 17);
	}

	public static int intervalYear(DateTime startDate, int startHour,
			DateTime endDate, int endHour) {
		if (startDate.getType() != 13) {
			throw new IllegalArgumentException(
					"startDate is not a validate DateTime which type is YEAR_TO_DAY");
		}
		if (endDate.getType() != 13) {
			throw new IllegalArgumentException(
					"endDate is not a validate DateTime which type is YEAR_TO_DAY");
		}
		startDate = new DateTime(startDate + " " + startHour, 14);
		endDate = new DateTime(endDate + " " + endHour, 14);
		int yearDiff = endDate.getYear() - startDate.getYear();
		if (endDate.getMonth() > startDate.getMonth())
			yearDiff++;
		else if (endDate.getMonth() < startDate.getMonth()) {
			yearDiff--;
		} else if (endDate.getDay() > startDate.getDay())
			yearDiff++;
		else if (endDate.getDay() < startDate.getDay()) {
			yearDiff--;
		} else if (endHour > startHour)
			yearDiff++;
		else if (endHour < startHour) {
			yearDiff--;
		}

		return yearDiff;
	}

	public static int intervalMonth(DateTime startDate, int startHour,
			DateTime endDate, int endHour) {
		if (startDate.getType() != 13) {
			throw new IllegalArgumentException(
					"startDate is not a validate DateTime which type is YEAR_TO_DAY");
		}
		if (endDate.getType() != 13) {
			throw new IllegalArgumentException(
					"endDate is not a validate DateTime which type is YEAR_TO_DAY");
		}
		startDate = new DateTime(startDate + " " + startHour, 14);
		endDate = new DateTime(endDate + " " + endHour, 14);
		int monthDiff = (endDate.getYear() - startDate.getYear()) * 12;
		if (endDate.getMonth() > startDate.getMonth()) {
			monthDiff += endDate.getMonth() - startDate.getMonth();
			if (endDate.getDay() > startDate.getDay())
				monthDiff++;
			else if (endDate.getDay() >= startDate.getDay()) {
				if (endDate.getHour() > startDate.getHour())
					monthDiff++;
			}
		} else if (endDate.getMonth() < startDate.getMonth()) {
			monthDiff += endDate.getMonth() - startDate.getMonth();
			if (endDate.getDay() <= startDate.getDay()) {
				if (endDate.getDay() < startDate.getDay()) {
					monthDiff--;
				} else if (endDate.getHour() > startDate.getHour())
					monthDiff++;
				else if (endDate.getHour() < startDate.getHour()) {
					monthDiff--;
				}
			}
		} else if (endDate.getDay() > startDate.getDay()) {
			monthDiff++;
		} else if (endDate.getDay() < startDate.getDay()) {
			monthDiff--;
		} else if ((endDate.getHour() <= startDate.getHour()) && 
				(endDate.getHour() < startDate.getHour())) {
				monthDiff--;
			}

		return monthDiff;
	}

	public static int intervalDay(DateTime startDate, int startHour,
			DateTime endDate, int endHour) {
		if (startDate.getType() != 13) {
			throw new IllegalArgumentException(
					"startDate is not a validate DateTime which type is YEAR_TO_DAY");
		}
		if (endDate.getType() != 13) {
			throw new IllegalArgumentException(
					"endDate is not a validate DateTime which type is YEAR_TO_DAY");
		}
		long diffTime = endDate.getTime() + endHour * 3600000L
				- (startDate.getTime() + startHour * 3600000L);

		int diffDay = (int) (diffTime / 86400000L);
		long diffT = diffTime - (diffDay * 86400000L);
		if (diffT > 0L)
			diffDay++;
		else if (diffT < 0L) {
			diffDay--;
		}
		return diffDay;
	}

	public DateTime addDay(int day) {
		DateTime dt = new DateTime(toString());
		dt.setTime(getTime() + day * 86400000L);
		return dt;
	}

	public DateTime addMonth(int iMonth) {
		DateTime dt = (DateTime) clone();
		GregorianCalendar gval = new GregorianCalendar();
		gval.setTime(dt);
		gval.add(2, iMonth);
		dt.setTime(gval.getTime().getTime());
		return dt;
	}

	public DateTime addYear(int iYear) {
		DateTime dt = (DateTime) clone();
		GregorianCalendar gval = new GregorianCalendar();
		gval.setTime(dt);
		gval.add(1, iYear);
		dt.setTime(gval.getTime().getTime());
		return dt;
	}

	public DateTime addHour(int hour) {
		DateTime dt = (DateTime) clone();
		dt.setTime(getTime() + hour * 3600000L);
		return dt;
	}

	public DateTime addMinute(int minute) {
		DateTime dt = (DateTime) clone();
		dt.setTime(getTime() + minute * 60000L);
		return dt;
	}

	public int getType() {
		return this.type;
	}

	private static String correct(String dateString) {
		String resultString = dateString;
		if (dateString.indexOf(47) > -1) {
			resultString = StringUtils.replace(dateString, "/", delimiter);
		}
		if (dateString.indexOf(45) > -1) {
			resultString = StringUtils.replace(dateString, "/", delimiter);
		}
		return resultString;
	}

	public boolean isEmpty() {
		return this.empty;
	}

	private void check(DateTime dateTime, int type) {
		if (dateTime.isEmpty()) {
			throw new IllegalStateException("DateTime is empty.");
		}
		int[] types = { 11, 12, 13, 14, 15, 16, 17, 22, 23, 24, 25, 26, 27, 33,
				34, 35, 36, 37, 44, 45, 46, 47, 55, 56, 57, 66, 67, 77 };

		boolean isValidType = false;
		for (int i = 0; i < types.length; ++i) {
			if (types[i] == type) {
				isValidType = true;
			}
		}

		if (!isValidType) {
			throw new IllegalStateException("this type is not support.");
		}

		if (dateTime.getType() == type)
			return;
		if (dateTime.getType() / 10 > type / 10) {
			throw new IllegalStateException(
					"this type is out of range of this datetime instance.");
		}

		if (dateTime.getType() % 10 < type % 10)
			throw new IllegalStateException(
					"this type is out of range of this datetime instance.");
	}

	public static DateTime timeZoneTransform(Date sourceDate, int sourceZone,
			int targetZone) {
		int type = 13;
		if (sourceDate instanceof DateTime) {
			type = ((DateTime) sourceDate).getType();
		}
		DateTime resultDateTime = new DateTime(new Date(), type);

		long newTime = sourceDate.getTime() + (targetZone - sourceZone)
				* 3600000L;
		resultDateTime.setTime(newTime);
		return resultDateTime;
	}

	public static int getDateInterval(Date startDate, Date endDate) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(startDate);
		calendar1.set(16, 0);
		calendar1.set(11, 0);
		calendar1.set(12, 0);
		calendar1.set(13, 0);
		calendar1.set(14, 0);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(endDate);
		calendar2.set(16, 0);
		calendar2.set(11, 0);
		calendar2.set(12, 0);
		calendar2.set(13, 0);
		calendar2.set(14, 0);
		int interval = (int) ((calendar2.getTimeInMillis() - calendar1
				.getTimeInMillis()) / 86400000L);

		calendar1.setTime(startDate);
		calendar1.set(16, 0);
		calendar1.set(1, 1970);
		calendar1.set(2, 1);
		calendar1.set(5, 1);
		calendar1.set(14, 0);
		calendar2.setTime(endDate);
		calendar2.set(16, 0);
		calendar2.set(1, 1970);
		calendar2.set(2, 1);
		calendar2.set(5, 1);
		calendar2.set(14, 0);
		if (calendar2.after(calendar1)) {
			interval++;
		}
		return interval;
	}

	public static DateTime getOffsetDate(Date baseDate, int dateInterval) {
		DateTime returnDateTime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		int baseDateInt = calendar.get(5);
		baseDateInt += dateInterval;
		calendar.set(5, baseDateInt);
		if (baseDate.getClass() == DateTime.class) {
			DateTime baseDateTime = (DateTime) baseDate;
			returnDateTime = new DateTime(calendar.getTime(), baseDateTime
					.getType());
		} else {
			returnDateTime = new DateTime(calendar.getTime(), 16);
		}
		return returnDateTime;
	}
}