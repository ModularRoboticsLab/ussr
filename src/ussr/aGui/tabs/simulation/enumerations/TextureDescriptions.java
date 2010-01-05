package ussr.aGui.tabs.simulation.enumerations;

import java.util.Vector;

import javax.swing.ImageIcon;

import ussr.aGui.MainFramesInter;
import ussr.aGui.tabs.TabsInter;
import ussr.description.setup.WorldDescription;
import ussr.description.setup.WorldDescription.TextureDescription;

/**
 * Contains constants of supported texture descriptions abstracted over their instances. 
 * NOTE NR.1: Add new textures  descriptions here in case of new ones required and in "WorldDescription". 
 * @author Konstantinas *
 */
public enum TextureDescriptions {
	
	/*Constants of texture descriptions represented in String format*/
	GRASS_TEXTURE (WorldDescription.GRASS_TEXTURE,TabsInter.GRASS_TEXTURE),
	GREY_GRID_TEXTURE(WorldDescription.GREY_GRID_TEXTURE,TabsInter.GREY_GRID_TEXTURE),
	MARS_TEXTURE(WorldDescription.MARS_TEXTURE,TabsInter.MARS_TEXTURE),
	WHITE_GRID_TEXTURE(WorldDescription.WHITE_GRID_TEXTURE,TabsInter.WHITE_GRID_TEXTURE),
	WHITE_TEXTURE(WorldDescription.WHITE_TEXTURE,TabsInter.WHITE_TEXTURE);


	/**
	 * The texture description.
	 */
	private TextureDescription textureDescription;
	
	private String fileName;
	
	/**
	 * The directory, where image is located in.
	 */
	private String imageDirectory;
	
	/**
	 * The icon created from image file.
	 */
	private ImageIcon imageIcon;

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	/**
	 * Contains supported texture descriptions abstracted over their instances. 
     * NOTE: Add new textures  descriptions here in case of new ones required. 
	 * @param textureDescription, a physics-engine independent description of a texture that can be loaded from a file.
	 */
	TextureDescriptions(TextureDescription textureDescription, String fileName){
		this.textureDescription = textureDescription;
		this.fileName = fileName;
		this.imageDirectory = formatIconDirectory(fileName);
		this.imageIcon = new ImageIcon(imageDirectory);
	}
	
	/**
	 * Returns instance of chosen texture description.
	 * @return instance of chosen texture description.
	 */
	public TextureDescription getTextureDecription(){
		return this.textureDescription;
	}
	
	/**
	 * Returns name of the file, texture description is associated with.
	 * @return name of the file, texture description is associated with.
	 */
	public String getRawFileDirectoryName(){
		return this.textureDescription.getFileName();
	}
	
	
	/**
	 * Formats directory, where icon is located.
	 * @param imageName, the name of the image file.
	 * @return directory, where icon is located.
	 */
	private static String formatIconDirectory(String imageName){
		return TabsInter.DIRECTORY_ICONS_TEXTURES+imageName+MainFramesInter.DEFAULT_ICON_EXTENSION1;
	}
	/**
	 * TODO
	 * Returns texture description constant from the 
	 * @param texture
	 * @return
	 */
	public static String texture(WorldDescription.TextureDescription texture){
		
		for (int textureNr=0;textureNr<TextureDescriptions.values().length;textureNr++){
			TextureDescriptions.values()[textureNr].equals(texture);
			return TextureDescriptions.values()[textureNr].name();
		}		
		return null;		
	}
	
	/**
 	 * Returns the name of chosen enumeration with changes in it such that Java convention for constants(upper case) is replaced wit lower and
 	 * underscore is replaced with space.
 	 * @return the name of chosen enumeration with changes in it such that Java convention for constants(upper case) is replaced wit lower and
 	 * underscore is replaced with space.
 	 */
 	public String getUserFriendlyName(){
 		char[] characters = this.toString().replace("TEXTURE", "").replace("_", " ").toLowerCase().toCharArray();
 		String name = (characters[0]+"").toUpperCase();
         for (int index =1;index<characters.length;index++){
         	name = name+characters[index];
         }		 
 		return name;
 	}
 	
 	public static Object[] getAllInUserFriendlyFromat(){
 		Vector <String> namesTetxtures = new Vector<String>();
 		for (int textureNr=0;textureNr<values().length;textureNr++){
 			namesTetxtures.add(values()[textureNr].getUserFriendlyName()) ;
 		} 		
 		return namesTetxtures.toArray();
 	}
 	
 	public static String toJavaUSSRConvention(String textureName){
 		return (textureName+ "TEXTURE").replace(" ", "_").toUpperCase(); 
 	}
	
	
	}
