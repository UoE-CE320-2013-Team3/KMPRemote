public class RemoteBluetoothClient{

	private	Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
	private	BluetoothAdapter deviceBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	public RemoteBluetoothClient(){			
			if(hasBluetoothAdapter()){
				if(!isBluetoothEnabled()){
					turnBluetoothOn();	
				}
			}else{
				//display message
			}			
			checkForPairedDevices();
	}
	private boolean hasBluetoothAdapter(){
		if (deviceBluetoothAdapter == null) {
			return false;
		}
		return true;
	}
		
	private boolean isBluetoothEnabled(){	
		return deviceBluetoothAdapter.isEnabled();
	}
	
	private void turnBluetoothOn(){
		if (!isBluetoothEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
	}	
	
	private void checkForPairedDevices(){	
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}	
	}
}