package sk.memdump.callguard.dto;

public class BlockingPhoneNumber {
	
	private long mId;
	private boolean mBlockIncoming;
	private boolean mBlockOutgoing;
	private String mPhoneNumber;
	
	
	public BlockingPhoneNumber(String phoneNumber, boolean bIncoming, boolean bOutgoing)
	{
		this(0,phoneNumber,bIncoming,bOutgoing);
	}
	
	public BlockingPhoneNumber(long id, String phoneNumber, int bIncoming, int bOutgoing)
	{
		this(id,phoneNumber,bIncoming == 1, bOutgoing == 1);
	}
	
	public BlockingPhoneNumber(long id, String phoneNumber, Integer bIncoming, Integer bOutgoing)
	{
		this(id,phoneNumber, false, false);

		final int bincmn = bIncoming != null ? bIncoming.intValue() : 0;
		if( bincmn == 1 )
		{
			this.turnOnBlockingIncomingCalls();
		}
		
		final int boutc = bOutgoing != null ? bOutgoing.intValue() : 0;
		if( boutc == 1 )
		{
			this.turnOnBlockingOutgoingCalls();
		}
		
	}
	
	public BlockingPhoneNumber(long id, String phoneNumber, boolean bIncoming, boolean bOutgoing)
	{
		setId(id);
		mPhoneNumber = phoneNumber;
		mBlockIncoming = bIncoming;
		mBlockOutgoing = bOutgoing;
	}

	public long getId() {
		return mId;
	}

	public void setId(long mId) {
		this.mId = mId;
	}
	
	public String getPhoneNumber()
	{
		return mPhoneNumber;
	}

	public boolean blockIncomingCalls() {
		return mBlockIncoming;
	}
	
	public boolean blockOutgoingCalls()
	{
		return mBlockOutgoing;
	}

	public void turnOnBlockingIncomingCalls() {
		this.mBlockIncoming = true;
	}
	
	public void turnOffBlockingIncomingCalls()
	{
		this.mBlockIncoming = false;
	}
	
	public void turnOnBlockingOutgoingCalls() {
		this.mBlockOutgoing = true;
	}
	
	public void turnOffBlockingOutgoingCalls()
	{
		this.mBlockOutgoing = false;
	}
}
