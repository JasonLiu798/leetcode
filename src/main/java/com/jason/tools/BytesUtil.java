package com.jason.tools;


import javax.imageio.stream.FileImageOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BytesUtil
{

	public static byte parseIntToByte(int num)
	{
		return (byte)num;
	}

	public static byte[] longToByteArray(long value)
	{
		byte[] b = new byte[8];
		for (int i = 0; i < 8; i++)
		{
			int offset = (b.length - 1 - i) * 8;
			b[i] = ((byte)(int)(value >>> offset & 0xFF));
		}
		return b;
	}

	public static byte[] ObjectToByte(Object obj)
	{
		byte[] bytes = null;
		try
		{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		}
		catch (Exception e)
		{
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}

	public static int bytes2int4(byte[] b)
	{
		int s = 0;
		s = (b[0] & 0xFF) << 24 | (b[1] & 0xFF) << 16 | (b[2] & 0xFF) << 8 | b[3] & 0xFF;
		return s;
	}

	public static int parseBytesToInt(byte[] bytes)
	{
		int length = bytes.length;
		int intValue = 0;
		for (int i = 0; i < length; i++)
		{
			intValue <<= 8;
			intValue |= bytes[i] & 0xFF;
		}
		return intValue;
	}

	public static String bdBytesToInt(String msgBytes)
	{
		String tempInt = Integer.valueOf(msgBytes, 2).toString();
		return tempInt;
	}

	public static int bytes2int2(byte[] b)
	{
		int s = 0;
		s = (b[0] & 0xFF) << 8 | b[1] & 0xFF;
		return s;
	}

	public static short parseBytesToShort(byte[] bytes)
	{
		int length = bytes.length;
		short intValue = 0;
		for (int i = 0; i < length; i++)
		{
			intValue = (short)(intValue << 8);
			intValue = (short)(intValue | bytes[i] & 0xFF);
		}
		return intValue;
	}

	public static String bcdToStr(byte[] bytes)
	{
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++)
		{
			temp.append((byte)((bytes[i] & 0xF0) >>> 4));
			temp.append((byte)(bytes[i] & 0xF));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	public static String BcdToStr(byte[] bytes)
	{
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++)
		{
			temp.append((byte)((bytes[i] & 0xF0) >>> 4));
			temp.append((byte)(bytes[i] & 0xF));
		}
		return temp.toString();
	}

	public static String bcdToStr(byte b)
	{
		StringBuffer temp = new StringBuffer(2);

		temp.append((byte)((b & 0xF0) >>> 4));
		temp.append((byte)(b & 0xF));

		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	public static long parseBytesToLong(byte[] bytes)
	{
		int length = bytes.length;
		long longValue = 0L;
		for (int i = 0; i < bytes.length; i++)
		{
			longValue <<= 8;
			longValue |= bytes[i] & 0xFF;
		}
		return longValue;
	}

	public static byte[] cutBytes(int byteIndex, int byteLength, byte[] bytes)
	{
		byte[] result = new byte[byteLength];
		System.arraycopy(bytes, byteIndex, result, 0, byteLength);
		return result;
	}

	public static int getBitValue(int byteIndex, int bitIndex, byte[] bytes)
	{
		byte byteValue = bytes[byteIndex];
		return byteValue >> 7 - bitIndex & 0x1;
	}

	public static boolean getBooleanValue(int byteIndex, int bitIndex, byte[] bytes)
	{
		byte byteValue = bytes[byteIndex];
		return (byteValue >> 7 - bitIndex & 0x1) == 1;
	}

	public static boolean getBooleanValues(int bitIndex, byte bytes)
	{
		String bytesToString = getBinaryStrFromByte(bytes);
		String bit = bytesToString.substring(bitIndex, bitIndex + 1);
		int bits = Integer.parseInt(bit);
		return bits == 1;
	}

	public static String getBinaryStrFromByte(byte b)
	{
		String result = "";
		byte a = b;
		for (int i = 0; i < 8; i++)
		{
			byte c = a;
			a = (byte)(a >> 1);
			a = (byte)(a << 1);
			if (a == c) {
				result = "0" + result;
			} else {
				result = "1" + result;
			}
			a = (byte)(a >> 1);
		}
		return result;
	}

	public static String hexString2binaryString(String hexString)
	{
		if ((hexString == null) || (hexString.length() % 2 != 0)) {
			return null;
		}
		String bString = "";
		for (int i = 0; i < hexString.length(); i++)
		{
			String tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString = bString + tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	public static byte getByte(int byteIndex, byte[] bytes)
	{
		return bytes[byteIndex];
	}

	public static byte[] getBigWord(int byteIndex, byte[] bytes)
	{
		byte[] result = new byte[2];
		result[1] = bytes[byteIndex];
		result[0] = bytes[(byteIndex + 1)];
		return result;
	}

	public static byte[] getBigDWord(int byteIndex, byte[] bytes)
	{
		byte[] result = new byte[4];
		result[3] = bytes[byteIndex];
		result[2] = bytes[(byteIndex + 1)];
		result[1] = bytes[(byteIndex + 2)];
		result[0] = bytes[(byteIndex + 3)];
		return result;
	}

	public static byte[] getWord(int byteIndex, byte[] bytes)
	{
		return cutBytes(byteIndex, 2, bytes);
	}

	public static byte[] getDWord(int byteIndex, byte[] bytes)
	{
		return cutBytes(byteIndex, 4, bytes);
	}

	public static int getBitsValue(int bitIndex, int bitLength, byte[] bytes)
	{
		int intValue = parseBytesToInt(bytes);
		int clearValue = 0;
		for (int i = 0; i < bitLength; i++)
		{
			clearValue <<= 1;
			clearValue++;
		}
		return intValue >> bytes.length * 8 - (bitIndex + bitLength - 1) & clearValue;
	}

	public static String bytesToHexString(byte[] src)
	{
		StringBuilder stringBuilder = new StringBuilder("");
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		for (int i = 0; i < src.length; i++)
		{
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] toStringHex(String hexString)
	{
		byte[] baKeyword = new byte[hexString.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try
			{
				baKeyword[i] = ((byte)(0xFF & Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16)));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return baKeyword;
	}

	public static byte[] strToBcd(String asc)
	{
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0)
		{
			asc = "0" + asc;
			len = asc.length();
		}
		byte[] abt = new byte[len];
		if (len >= 2) {
			len /= 2;
		}
		byte[] bbt = new byte[len];
		abt = asc.getBytes();
		for (int p = 0; p < asc.length() / 2; p++)
		{
			int j;
			if ((abt[(2 * p)] >= 48) && (abt[(2 * p)] <= 57))
			{
				j = abt[(2 * p)] - 48;
			}
			else
			{
				if ((abt[(2 * p)] >= 97) && (abt[(2 * p)] <= 122)) {
					j = abt[(2 * p)] - 97 + 10;
				} else {
					j = abt[(2 * p)] - 65 + 10;
				}
			}
			int k;
			if ((abt[(2 * p + 1)] >= 48) && (abt[(2 * p + 1)] <= 57))
			{
				k = abt[(2 * p + 1)] - 48;
			}
			else
			{
				if ((abt[(2 * p + 1)] >= 97) && (abt[(2 * p + 1)] <= 122)) {
					k = abt[(2 * p + 1)] - 97 + 10;
				} else {
					k = abt[(2 * p + 1)] - 65 + 10;
				}
			}
			int a = (j << 4) + k;
			byte b = (byte)a;
			bbt[p] = b;
		}
		return bbt;
	}

	public static byte[] int2bytes2(int value)
	{
		byte[] ret = new byte[2];
		ret[1] = ((byte)(value & 0xFF));
		value >>= 8;
		ret[0] = ((byte)(value & 0xFF));
		return ret;
	}

	public static byte[] int2bytes4(int value)
	{
		int temp = value;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++)
		{
			b[i] = new Integer(temp & 0xFF).byteValue();
			temp >>>= 8;
		}
		return b;
	}

	public static byte[] intToByteArray(int value)
	{
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++)
		{
			int offset = (b.length - 1 - i) * 8;
			b[i] = ((byte)(value >>> offset & 0xFF));
		}
		return b;
	}

	public static Date str2Date(String dateStr)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		Date dates = null;
		try
		{
			dates = format.parse(dateStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dates;
	}

	public static String stringToAscii(String value)
	{
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (i != chars.length - 1) {
				sbu.append(chars[i]).append(",");
			} else {
				sbu.append(chars[i]);
			}
		}
		return sbu.toString();
	}

	public static double formatMileageData(double mileage)
	{
		DecimalFormat df = new DecimalFormat("###.00");
		return Double.valueOf(df.format(mileage)).doubleValue();
	}

	public static final byte[] Integer4HexBytes(int src, int bit)
	{
		byte[] yaIntDatas = new byte[4];
		for (int i = bit - 1; i >= 0; i--) {
			yaIntDatas[(bit - 1 - i)] = ((byte)(src >> 8 * i & 0xFF));
		}
		return yaIntDatas;
	}

	public static final byte[] Integer2HexBytes(int src, int bit)
	{
		byte[] yaIntDatas = new byte[2];
		for (int i = bit - 1; i >= 0; i--) {
			yaIntDatas[(bit - 1 - i)] = ((byte)(src >> 8 * i & 0xFF));
		}
		return yaIntDatas;
	}

	public static long strToDateLong(String strDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate.getTime();
	}

	public static String toDateFormat(Date date)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sTime = format.format(date);
		return sTime;
	}

	public static byte[] intToHex(int value)
	{
		byte[] resultbyte = new byte[2];

		String resulta = Integer.toHexString(value);
		while (resulta.length() < 4) {
			resulta = "0" + resulta;
		}
		resultbyte[0] = Byte.parseByte(resulta.substring(0, 2));
		resultbyte[1] = Byte.parseByte(resulta.substring(2));

		return resultbyte;
	}

	public static String getHexString(int length)
	{
		String resulStr = Integer.toHexString(length);
		while (resulStr.length() < 4) {
			resulStr = "0" + resulStr;
		}
		return resulStr;
	}

	public static String getIntToHex(int values)
	{
		String msg = Integer.toHexString(values);
		if (msg.length() == 1) {
			msg = "000" + msg;
		} else if (msg.length() == 2) {
			msg = "00" + msg;
		} else if (msg.length() == 3) {
			msg = "0" + msg;
		} else {
			msg = msg;
		}
		return msg;
	}

	public static String getIntToBanary(int values)
	{
		String msg = Integer.toBinaryString(values);
		if (msg.length() == 1) {
			msg = "000" + msg;
		} else if (msg.length() == 2) {
			msg = "00" + msg;
		} else if (msg.length() == 3) {
			msg = "0" + msg;
		} else {
			msg = msg;
		}
		return msg;
	}

	public static String intTo32Binary(int n)
	{
		String str = "";
		for (int i = -2147483648; i != 0; i >>>= 1) {
			str = str + ((n & i) == 0 ? '0' : '1');
		}
		return str;
	}

	public static String stringReverse(String msg)
	{
		StringBuilder sb = new StringBuilder(msg);
		sb.reverse();
		return sb.toString();
	}

	public static void byte2image(byte[] data, String path)
	{
		if ((data.length < 3) || (path.equals(""))) {
			return;
		}
		try
		{
			FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("Make Picture success,Please find image in " + path);
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}

	public static Date StrToDate(String str)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try
		{
			date = format.parse(str);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String[] args) {}
}

