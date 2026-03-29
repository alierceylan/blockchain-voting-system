import java.util.ArrayList;
import java.util.List;

public class Blockchain {

	private List<Block> chain;
	

	public Blockchain() {
		chain = new ArrayList<Block>();
		chain.add(generateGenesis());
	}
	
	private Block generateGenesis() {
		Block genesis = new Block(new java.util.Date(),"<nonce>","<sifreli_kimlik>","<muhtarlýk>","<ilce_bel>","<bel_mec>","<buyuk_bel>","<imza>");
		genesis.setPreviousHash(null);
		genesis.computeHash();
		return genesis;
	}
	
	public void addBlock(Block blk) {
		Block newBlock = blk;
		newBlock.setPreviousHash(chain.get(chain.size()-1).getHash());
		newBlock.computeHash();
		this.chain.add(newBlock);
	}
	
	public void displayChain(ArrayList<String> blok_bilg,ArrayList<String> blok_bilg_0) {
		System.out.println("----------------------BLOKZÝNCÝR-----------------");
		for(int i=0; i<chain.size(); i++) {
			System.out.println("Block index: " + i);
			System.out.println("Zaman Damgasý: " + chain.get(i).getTimestamp());
			System.out.println("Nonce: " + chain.get(i).getNonce());			
			System.out.println("Þifreli_Kimlik_Bilgileri:  " + chain.get(i).getSifreli_kimlik());
			System.out.println("Muhtarlýk:  " + chain.get(i).getMuht());
			System.out.println("Ýlçe Belediyesi:  " + chain.get(i).getIlce());
			System.out.println("Belediye Meclisi:  " + chain.get(i).getBel_mec());
			System.out.println("Büyüksehir Bel:  " + chain.get(i).getBuyuk_bel());
			System.out.println("Ýmza:  " + chain.get(i).getImza());
			System.out.println("Prev_Hash: " + chain.get(i).getPreviousHash());
			System.out.println("Hash: " + chain.get(i).getHash());			
			System.out.println("---------------------------------------");
			System.out.println();
		  
			
			
			if(i==0)
			{  
				blok_bilg_0.add("0");
				blok_bilg_0.add(chain.get(0).getTimestamp().toString());
				blok_bilg_0.add(chain.get(0).getNonce());
				blok_bilg_0.add(chain.get(0).getPreviousHash());
				blok_bilg_0.add(chain.get(0).getHash());
				blok_bilg_0.add(chain.get(0).getSifreli_kimlik());
				blok_bilg_0.add(chain.get(0).getMuht());
				blok_bilg_0.add(chain.get(0).getIlce());
				blok_bilg_0.add(chain.get(0).getBel_mec());
				blok_bilg_0.add(chain.get(0).getBuyuk_bel());
				blok_bilg_0.add(chain.get(0).getImza());
				Show_other_blocks sob= new Show_other_blocks(blok_bilg_0);
				sob.setVisible(true);
			}
			if(i==1)
			{   blok_bilg_0.clear();
				blok_bilg_0.add("1");
				blok_bilg_0.add(chain.get(1).getTimestamp().toString());
				blok_bilg_0.add(chain.get(1).getNonce());
				blok_bilg_0.add(chain.get(1).getPreviousHash());
				blok_bilg_0.add(chain.get(1).getHash());
				blok_bilg_0.add(chain.get(1).getSifreli_kimlik());
				blok_bilg_0.add(chain.get(1).getMuht());
				blok_bilg_0.add(chain.get(1).getIlce());
				blok_bilg_0.add(chain.get(1).getBel_mec());
				blok_bilg_0.add(chain.get(1).getBuyuk_bel());
				blok_bilg_0.add(chain.get(1).getImza());
				Show_other_blocks sob= new Show_other_blocks(blok_bilg_0);
				sob.setVisible(true);
			}
			if(i==2)
			{   blok_bilg_0.clear();
				blok_bilg_0.add("2");
				blok_bilg_0.add(chain.get(2).getTimestamp().toString());
				blok_bilg_0.add(chain.get(2).getNonce());
				blok_bilg_0.add(chain.get(2).getPreviousHash());
				blok_bilg_0.add(chain.get(2).getHash());
				blok_bilg_0.add(chain.get(2).getSifreli_kimlik());
				blok_bilg_0.add(chain.get(2).getMuht());
				blok_bilg_0.add(chain.get(2).getIlce());
				blok_bilg_0.add(chain.get(2).getBel_mec());
				blok_bilg_0.add(chain.get(2).getBuyuk_bel());
				blok_bilg_0.add(chain.get(2).getImza());
				Show_other_blocks sob= new Show_other_blocks(blok_bilg_0);
				sob.setVisible(true);
			}
		}
		
		blok_bilg.add("3");
		blok_bilg.add(chain.get(3).getTimestamp().toString());
		blok_bilg.add(chain.get(3).getNonce());
		blok_bilg.add(chain.get(3).getPreviousHash());
		blok_bilg.add(chain.get(3).getHash());
		
	}
	
	public Block getLatestBlock() {
		return this.chain.get(chain.size()-1);
	}
	
	public void isValid() {
		
		for(int i=chain.size()-1; i>0; i--) {
			if(   !(chain.get(i).getHash().equals(chain.get(i).computeHash()))   ) {
				System.out.println("Chain is not valid");
				return;
			}
			
			if(  !(chain.get(i).getPreviousHash().equals(chain.get(i-1).computeHash()))  ) {
				System.out.println("Chain is not valid");
				return;
			}
		}
		
		System.out.println("Chain is valid.");
		
	}
	
	
}
