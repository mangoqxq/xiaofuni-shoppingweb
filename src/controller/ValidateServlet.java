package controller;

import java.io.IOException;
import java.util.Random;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
// ����֤��ͼƬ���Խ��г�ʼ��
	private int width = 90;
	private int height =20 ;
// ������֤���ַ�����Ϊ 4
	private int codeCount = 4;

	private int x = 0;
// ����߶�
	private int fontHeight;
	private int codeY;
// ���������ֵ䣬�����ӱܿ����׻�������ĸ������
	char[] codeSequence = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// ����һ���������������
		Random random = new Random();

// 1������һ��ͼƬ
		x = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;
// ����ͼ�� buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
// ��ͼ�����Ϊ��ɫ
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
// �������壬����Ĵ�СӦ�ø���ͼƬ�ĸ߶�����
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
// ��������
		g.setFont(font);
// ���߿�
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
// ������� 8 �������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(Color.BLACK);

		for (int i = 0; i < 8; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(8);
			int yl = random.nextInt(8);
			g.drawLine(x, y, x + xl, y + yl);
		}

// 2�����������
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
// ������� codeCount �����ֵ���֤��
		for (int i = 0; i < codeCount; i++) {
// �õ������������֤������
			String strRand = String.valueOf(codeSequence[random.nextInt(54)]);
// �����������ɫ������������ɫֵ�����������ÿλ���ֵ���ɫֵ������ͬ
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
// �������������ɫ����֤����Ƶ�ͼ����
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);
// ���������ĸ�����������һ��
			randomCode.append(strRand);
		}

// 3������������� session ��
		HttpSession session = request.getSession();
		session.setAttribute("validateCode", randomCode.toString());

// 4�����ͼƬ����ֹͼ�񻺴�
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");

// ��ͼ������� Servlet �������
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", out);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
