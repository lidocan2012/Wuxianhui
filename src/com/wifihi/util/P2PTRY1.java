package com.wifihi.util;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class P2PTRY1 extends Frame/*����*/
{
	/*��������*/
	List IPlst=new List(10);
	List lst=new List(6);
	TextField IPtf=new TextField(15);
	TextField Messagetf=new TextField(50);
	Label lb1=new Label("IP�б�:");
	Label lb2=new Label("�ı�����:");
	Label lb3=new Label("Ŀ��IP:");
	Button buto1=new Button("�����ļ�");
	Button buto2=new Button("ˢ��IP�б�");
	DatagramSocket ds=null;/*DataSocket�Ĺ��캯�����׳��쳣������ֱ�Ӵ�������*/
	DatagramSocket sendsocket=null;
	DatagramSocket receivesocket=null;
	ServerSocket sfres=null;
	Socket resocket=null;
	String filename;
	String filepath;
	
	public P2PTRY1()/*���๹�췽��*/
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
		p2.add(lb2,"Center");/*���ڴ���*/

		
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

		}).start();/*�����߳�*/


		Messagetf.addActionListener(new ActionListener()/*�����¼�������*/
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
					Messagetf.setText("");/*�����Ϣ�ı�������*/
			}
		});/*������Ϣ��*/
				
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				ds.close();
				dispose();
				System.exit(0);
			}

		});	/*���������⣿*/
																																										
		
/*******************************************************************************************/
		/*���ʹ�����   {   */

		 class Sendwindow extends Frame
		{
			List Filelst=new List(15);
			TextField Dir=new TextField(50);
			Label lb1=new Label("�ļ��б�:");
			Label lb2=new Label("�����ļ�·��:");
			Button bu2=new Button("ȡ��");
			Button bu1=new Button("����");
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
					 Button ye=new Button("��");
					 Button no=new Button("��");
					 Label slb=new Label("Ҫ����"+Filelst.getSelectedItem()+"����ļ���");
					 File sendfile=new File(Filelst.getSelectedItem());
					 					 					 
					 
						class Sending
/*���Ͳ���->*/						{
							
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
								System.out.println("�ļ����ȣ�"+length);

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
								 
									
								 if(e2.getActionCommand().equals("��"))
/*���ʹ�����->*/				{
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
						 Chowin.setTitle("����ȷ��");
						 Chowin.setVisible(true);
						 Chowin.setResizable(false);
					 }
					 					 				 
				 }
												
				
				/****************************************/
				
				
			
				bu2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(e.getActionCommand().equals("ȡ��"))
							System.exit(0);/*�������⣿*/
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
							else System.out.println("�����ڸ��ļ�");/*����ӷ��ضԻ���*/
																			
					}
				});
								
				bu1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(e.getActionCommand().equals("����")&&Filelst.getSelectedItem()!=null)
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
				Filesend.setTitle("�ļ�Ŀ¼");
				Filesend.setVisible(true);
				Filesend.setResizable(false);
			}/*�������ʹ��ں���*/
		}


		/*    }    ���ʹ�����*/
/*****************************************************************************************/
		 
/*->���մ���*/
		 class Receivewin extends Frame
		{
			String newfilename;
			Label recomand=new Label("�����뱣��·�������ļ�����");
			Button rc=new Button("����");
			Button rf=new Button("�ܾ�");
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
				filereceive.setTitle("�յ����ļ���");
				filereceive.setVisible(true);
				filereceive.setResizable(false);			
				rc.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						
						if(e.getActionCommand().equals("����"))
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
							System.out.println("�ļ�������ɣ�");
							}
							catch(IOException ex){ex.printStackTrace();}
						}
						/*->�����������ڷ���<-*/
					}
				});

			}		
		}
		 
		 
		 
/*->���ռ����߳�*/	
		 
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


			}).start();/*�����߳�*/
		 
		 
		 
			buto1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(e.getActionCommand().equals("�����ļ�"))
						{
							Sendwindow swin=new Sendwindow();
							swin.Sendwin(swin);
						}
				}
			}
			
			);	/*�������ʹ���*/		
						
		
	}/*���캯��*/
	

	
	public static void main(String args[])
	{
		System.out.println("Starting...");
		P2PTRY1 mainframe=new P2PTRY1();/*����ʵ��*/
		mainframe.setSize(400,400);/*���ô��ڴ�С*/
		mainframe.setTitle("P2P");
		mainframe.setVisible(true);
		mainframe.setResizable(false);/*�̶����ڴ�С*/
	
	}
}



