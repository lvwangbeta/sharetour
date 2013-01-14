package com.sharetour.control;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class ImgDownServlet
 */
public class ImgDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");   //[0-9]+.jpg
		
		MongoClient mongo = new MongoClient("172.30.48.73", 25273);
		DB db = mongo.getDB("db");
		db.authenticate("b2b10f1d-b31c-4a10-b6d7-b4914bb48292", "de446439-2fc5-43ec-8c0f-7568932b3d27".toCharArray());
		String filename = id;
		GridFS photo = new GridFS(db, "imgs");
		GridFSDBFile imgout = photo.findOne(filename);
		
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		BufferedImage img = ImageIO.read(imgout.getInputStream());
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(img);
        out.close();
        mongo.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
