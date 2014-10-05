package com.wifihi.util;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class P2PTRY1 extends Frame/*主类*/
{
	/*对象声明*/
	List IPlst=new List(10);
	List lst=new List(6);
	TextField IPtf=new TextField(15);
	TextField Messagetf=new TextField(50);
	Label lb1=new Label("IP列表:");
	Label lb2=new Label("文本输入:");
	Label lb3=new Label("目标IP:");
	Button buto1=new Button("发送文件");
	Button buto2=new Button("刷新IP列表");
	DatagramSocket ds=null;/*DataSocket的构造函数会抛出异常，不能直接创建对象*/
	DatagramSocket sendsocket=null;
	DatagramSocket receivesocket=null;
	ServerSocket sfres=null;
	Socket resocket=null;
	String filename;
	String filepath;
	
	public P2PTRY1()/*主类构造方法*/
	{
	try
		{
			ds=new DatagramSocket(3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
						
		Panel p1=new Panel();
		Panel p3=new Panel();
		this.add(p1,"North");
		p1.setLayout(new BorderLayout());
		p1.add(lb1,"North");
		this.add(p3,"South");
		p3.setLayout(new BorderLayout());
		p3.add(lb3,"North");
		p1.add(IPlst);
		p3.add(IPtf,"West");
		p3.add(buto1,"East");
		p3.add(buto2,"Center");
		Panel p2=new Panel();
		this.add(p2,"Center");
		p2.setLayout(new BorderLayout());
		p2.add(Messagetf,"South");
		p2.add(lst,"North");
		p2.add(lb2,"Center");/*窗口代码*/

		
		new Thread(new Runnable()
		{
			public void run()
			{
				byte buf[]=new byte[1024];
				DatagramPacket dp=new DatagramPacket(buf,1024);
				while(true)
				{
					try
					{
						ds.receive(dp);
						lst.add(new String(buf,0,dp.getLength())
							+"  from  "+dp.getAddress().getHostAddress()+":"+dp.getPort(),0);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}

				}
			}

		}).start();/*接收线程*/


		Messagetf.addActionListener(new ActionListener()/*新增事件处理器*/
		{
			public void actionPerformed(ActionEvent e)
			{
				byte [] buf;
				buf=Messagetf.getText().getBytes();
				try
				{
					DatagramPacket dp=new DatagramPacket(buf,buf.length,
						InetAddress.getByName(IPtf.getText()),3000);
					ds.send(dp);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
					Messagetf.setText("");/*清空消息文本框内容*/
			}
		});/*发送信息块*/
				
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				ds.close();
				dispose();
				System.exit(0);
			}

		});	/*？存在问题？*/
																																										
		
/*******************************************************************************************/
		/*发送窗口类   {   */

		 class Sendwindow extends Frame
		{
			List Filelst=new List(15);
			TextField Dir=new TextField(50);
			Label lb1=new Label("文件列表:");
			Label lb2=new Label("输入文件路径:");
			Button bu2=new Button("取消");
			Button bu1=new Button("发送");
			File fdir=null;

			
			public  Sendwindow()
			{
				Panel p1=new Panel();
				Panel p2=new Panel();
				Panel p3=new Panel();
				this.add(p1,"North");
				this.add(p2,"Center");
				this.add(p3,"South");
				p1.setLayout(new BorderLayout());
				p2.setLayout(new BorderLayout());
				p3.setLayout(new BorderLayout());
				p1.add(lb1,"North");
				p1.add(Filelst,"Center");
				p2.add(lb2,"North");
				p2.add(Dir,"Center");
				p3.add(bu1,"West");
				p3.add(bu2,"East");
				
	/*********************************************/
	
				class Chioce_1 extends Frame
				 {
					 Button ye=new Button("是");
					 Button no=new Button("否");
					 Label slb=new Label("要发送"+Filelst.getSelectedItem()+"这个文件吗？");
					 File sendfile=new File(Filelst.getSelectedItem());
					 					 					 
					 
						class Sending
/*发送部分->*/						{
							
							Socket fses=null;
							public void sender(String filename)
							{
								
								try{								
									fses=new Socket(InetAddress.getByName(IPtf.getText()),3200);
									fses.setSoTimeout(5000);
									}
								catch(Exception e1)
								{e1.getStackTrace();}
								
								byte sendbuf[]=new byte[10240];
								DataOutputStream outs=null;
								DataInputStream ins=null;
								Socket ack=null;
								boolean a=true;
								String ackstring=null;
							
								try{
								outs=new DataOutputStream(new BufferedOutputStream(fses.getOutputStream()));
								File sendfile=new File(filename);
								FileInputStream fis=new FileInputStream(sendfile);								
								int length=fis.read(sendbuf);
								System.out.println("文件长度："+length);

									outs.write(sendbuf,0,length);
									outs.flush();
									fis.close();
									fses.close();					
								
							}								
								catch (IOException e) 
								{e.printStackTrace();}
							}
						}					 												
						
					 public Chioce_1()
					 {
						 this.add(ye,"East");
						 this.add(no,"West");
						 this.add(slb,"North");
						 File sendfile=new File(Filelst.getSelectedItem());
						 						 						 						 						 
					 
						 ye.addActionListener(new ActionListener()
						 {
							 public void actionPerformed(ActionEvent e2)
							 {
								 
									
								 if(e2.getActionCommand().equals("是"))
/*发送处理部分->*/				{
									 System.out.println(Filelst.getSelectedItem()+"  to  "+IPtf.getText());
									 filename=Filelst.getSelectedItem();
									 Sending sd=new Sending();
									 sd.sender(Filelst.getSelectedItem());
								 }
							 }
						 					 						 						 
						 });
					 }
					 
					 
					 public void Cho1(Chioce_1 Chowin)
					 {
						 Chowin.setSize(250,85);
						 Chowin.setTitle("发送确认");
						 Chowin.setVisible(true);
						 Chowin.setResizable(false);
					 }
					 					 				 
				 }
												
				
				/****************************************/
				
				
			
				bu2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(e.getActionCommand().equals("取消"))
							System.exit(0);/*？有问题？*/
					}
				});
				
							
				Dir.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String formerdir=Dir.getText();
					    File fdir=new File(Dir.getText());
					    String f_array[];
						f_array=fdir.list();
						
						if(formerdir!=Dir.getText())
							Filelst.removeAll();
							if(fdir.exists())
							{
								if(fdir.isDirectory())
								{
									for(int i=0;i<f_array.length;i++)
									Filelst.add(Dir.getText()+"\\"+f_array[i]);
									filepath=Dir.getText()+"\\"+Filelst.getSelectedItem();
								
								}
								else{ Filelst.add(Dir.getText());filepath=Dir.getText();}
							}
							else System.out.println("不存在该文件");/*可添加返回对话框*/
																			
					}
				});
								
				bu1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(e.getActionCommand().equals("发送")&&Filelst.getSelectedItem()!=null)
						{
							Chioce_1 chiowin=new Chioce_1();
							chiowin.Cho1(chiowin);
						}
						
					}
				});					
				
			}
												
			public void Sendwin(Sendwindow Filesend)
			{
				Filesend.setSize(350,350);
				Filesend.setTitle("文件目录");
				Filesend.setVisible(true);
				Filesend.setResizable(false);
			}/*创建发送窗口函数*/
		}


		/*    }    发送窗口类*/
/*****************************************************************************************/
		 
/*->接收窗口*/
		 class Receivewin extends Frame
		{
			String newfilename;
			Label recomand=new Label("请输入保存路径及新文件名：");
			Button rc=new Button("接收");
			Button rf=new Button("拒绝");
			TextField filepath=new TextField(30);
			Socket receive=null;		
			public void recwin(Receivewin filereceive,Socket dow)
			{
				receive=dow;
				this.add(rc,"East");
				this.add(rf,"West");
				this.add(recomand,"North");
				this.add(filepath,"Center");
				filereceive.setSize(250,80);
				filereceive.setTitle("收到新文件！");
				filereceive.setVisible(true);
				filereceive.setResizable(false);			
				rc.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						
						if(e.getActionCommand().equals("接收"))
						{
							byte recbuf[]=new byte[10240];
							try{
							DataInputStream ins=new DataInputStream(new BufferedInputStream(receive.getInputStream()));
							File newfile=new File(filepath.getText());
							FileOutputStream fos=new FileOutputStream(newfile);
							ins.read(recbuf);
							fos.write(recbuf);
							fos.close();
							ins.close();
							System.out.println("文件接收完成！");
							}
							catch(IOException ex){ex.printStackTrace();}
						}
						/*->插入消除窗口方法<-*/
					}
				});

			}		
		}
		 
		 
		 
/*->接收监听线程*/	
		 
		 new Thread(new Runnable()
			{
				public void run()
				{
					try{
							
					ServerSocket sfres=new ServerSocket(3200);
					Socket down=sfres.accept();
					Receivewin receivewin=new Receivewin();
					receivewin.recwin(receivewin,down);
					System.out.println(down.getInetAddress());
					}
					catch(IOException ex){ex.printStackTrace();}

					
				}


			}).start();/*接收线程*/
		 
		 
		 
			buto1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(e.getActionCommand().equals("发送文件"))
						{
							Sendwindow swin=new Sendwindow();
							swin.Sendwin(swin);
						}
				}
			}
			
			);	/*创建发送窗口*/		
						
		
	}/*构造函数*/
	

	
	public static void main(String args[])
	{
		System.out.println("Starting...");
		P2PTRY1 mainframe=new P2PTRY1();/*创建实例*/
		mainframe.setSize(400,400);/*设置窗口大小*/
		mainframe.setTitle("P2P");
		mainframe.setVisible(true);
		mainframe.setResizable(false);/*固定窗口大小*/
	
	}
}



