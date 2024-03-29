package frc.robot.utils.frc4048;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Logging {

	public static enum Subsystems {
		ARM, BALLSTORAGE, CONTROLPANEL, DRIVE, FLYWHEEL, INTAKE, LIFT, PNEUMATICS
	}

	private boolean writeLoggingGap = false;
	private static final int MSG_QUEUE_DEPTH = 512;
	private java.util.Timer executor;
	private long period;
	private WorkQueue wq;
	public static DecimalFormat df5 = new DecimalFormat(".#####");
	public static DecimalFormat df4 = new DecimalFormat(".####");
	public static DecimalFormat df3 = new DecimalFormat(".###");
	private final static ArrayList<LoggingContext> loggingContexts = new ArrayList<LoggingContext>();

	public Logging(long period, WorkQueue wq) {
		this.period = period;
		this.wq = wq;
	}
	
	abstract static public class LoggingContext {
		private int counter = 0;
		private final Subsystems subsystem;
		private final StringBuilder sb = new StringBuilder();
		private static final char COMMA = ',';
		private static final char QUOTE = '"';
		private boolean writeTitles = false;
		
		public LoggingContext(final Subsystems subsystem) {
			this.subsystem = subsystem;
			loggingContexts.add(this);
		}

		abstract protected void addAll();
		
		final void writeHeadings() {
			writeTitles = true;
			writeData();
			writeTitles = false;
		}
		
		public final void writeData() {
			if ((DriverStation.getInstance().isEnabled() && (counter % 5 == 0)) || writeTitles) {
				sb.setLength(0);
				sb.append(df3.format(Timer.getFPGATimestamp()));
				sb.append(",");
				if(DriverStation.getInstance().isDisabled())
					sb.append(0);
				else
					sb.append(df3.format(Timer.getFPGATimestamp()));
				sb.append(",");
				sb.append(subsystem.name());
				sb.append(",");
				addAll();
				Robot.logging.traceMessage(sb);
			}
		}
		
		protected void add(String title, int value) {
			if (writeTitles) {
				sb.append(QUOTE).append(title).append(QUOTE);
			}
			else {
				sb.append(Integer.toString(value));
			}
			sb.append(COMMA);
        }
		
		/*protected void add(String title, WristState value) {
			if (writeTitles) {
				sb.append(QUOTE).append(title).append(QUOTE);
			}
			else {
				sb.append(value);
			}
			sb.append(COMMA);
		}*/

		protected void add(String title, boolean value) {
			if (writeTitles) {
				sb.append(QUOTE).append(title).append(QUOTE);
			}
			else {
				sb.append(Boolean.toString(value));
			}
			sb.append(COMMA);
		}
		
		protected void add(String title, double value) {
			if (writeTitles) {
				sb.append(QUOTE).append(title).append(QUOTE);
			}
			else {
				sb.append(Double.toString(value));
			}
			sb.append(COMMA);
		}
		
		protected void add(String title, String value) {
			if (writeTitles) {
				sb.append(QUOTE).append(title).append(QUOTE);
			}
			else {
				sb.append(QUOTE).append(value).append(QUOTE);
			}
			sb.append(COMMA);
		}
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new ConsolePrintTask(wq, this), 0L, this.period);
	}

//	public void traceSubsystem(LoggingContext context, boolean alwaysPrint, double... vals) {
//		traceSubsystem(context, alwaysPrint, vals, (String[]) null);
//	}
	
//	public void traceSubsystem(LoggingContext context, boolean alwaysPrint, String... vals) {
//		traceSubsystem(context, true, (double[]) null, vals);
//	}

//	public void traceSubsystem(LoggingContext context, String vals1[], double... vals2) {
//		traceSubsystem(context, false, vals2, vals1);
//	}

//	public void traceSubsystem(LoggingContext context, boolean alwaysPrint, double vals1[], String... vals2) {
//		boolean printThis = alwaysPrint;
//		if (!printThis) {
//			printThis = DriverStation.getInstance().isEnabled() && (context.counter % 5 == 0);
//			context.counter += 1;
//		}
//
//		if (printThis) {
//			final StringBuilder sb = new StringBuilder();
//			sb.append(df3.format(Timer.getFPGATimestamp()));
//			sb.append(",");
//			sb.append(context.subsystem.name());
//			sb.append(",");
//			if (vals1 != null) {
//				for (final double v : vals1) {
//					sb.append(df5.format(v));
//					sb.append(",");
//				}
//			}
//			if (vals2 != null) {
//				for (final String v : vals2) {
//					sb.append("\"").append(v).append("\"");
//					sb.append(",");
//				}
//			}
//			traceMessage(sb);
//		}
//	}

	private void traceMessage(final StringBuilder sb) {
		if (writeLoggingGap) {
			if (wq.append("LOGGING GAP!!"))
				writeLoggingGap = false;
		}
		if (!wq.append(sb.toString()))
			writeLoggingGap = true;
	}

	public void traceMessage(String ...vals) {
		final StringBuilder sb = new StringBuilder();
		sb.append(df3.format(Timer.getFPGATimestamp()));
		sb.append(",");
		if(DriverStation.getInstance().isDisabled())
			sb.append(0);
		else
			sb.append(df3.format(Timer.getFPGATimestamp() - Robot.timeOfStart));
		sb.append(",");
		sb.append(",");
		if (vals != null) {
			for (final String v : vals) {
				sb.append("\"").append(v).append("\"");
				sb.append(",");
			}
		}
		traceMessage(sb);
	}

    /**
     * Iterate through the known logging contexts and write the data for each of
     * them. Logs one logging context every time it'called. It's called by the
     * period() method and we want to spread the cost of logging over multiple calls
     * so we don't run over the 20ms budget.
     */
    public void writeAllData() {
        for (final LoggingContext lc : loggingContexts) {
            lc.writeData();
        }
    }

    /**
     * Iterate through all known logging contexts and write the title for each of
     * them. The #writeAllData and #writeAllTitles functions must iterate through
     * the contexts in the same order so the titles and data are corresponding.
     */
    public void writeAllHeadings() {
        for (final LoggingContext lc : loggingContexts) {
            lc.writeHeadings();
        }
    }

	private class ConsolePrintTask extends TimerTask {
		PrintWriter log;
		final WorkQueue wq;
		final Logging l;

		private ConsolePrintTask(WorkQueue wq, Logging l) {
			this.l = l;
			this.wq = wq;
			log = null;
		}

		public void print() {
			// Log all events, we want this done also when the robot is disabled
			for (;;) {
				final String message = wq.getNext();
				if (message != null)
					log.println(message);
				else
					break;
			}
			log.flush();
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			if (log == null) {
				try {
					File file = new File("/home/lvuser/Logs");
					if (!file.exists()) {
						if (file.mkdir()) {
							System.out.println("Log Directory is created!");
						} else {
							System.out.println("Failed to create Log directory!");
						}
					}
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_ss-SSS");
					dateFormat.setTimeZone(TimeZone.getTimeZone("CST6CDT"));
					try {
						this.log = new PrintWriter("/media/sda1/" + dateFormat.format(date) + "-Log.csv", "UTF-8");
					} catch (Exception e) {
						this.log = new PrintWriter("/home/lvuser/Logs/" + dateFormat.format(date) + "-Log.txt",
								"UTF-8");
					}

					log.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			print();
		}
    }
}