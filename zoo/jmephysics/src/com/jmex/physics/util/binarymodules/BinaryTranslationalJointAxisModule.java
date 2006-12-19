package com.jmex.physics.util.binarymodules;

import java.io.IOException;

import com.jme.util.export.InputCapsule;
import com.jme.util.export.Savable;
import com.jme.util.export.binary.BinaryLoaderModule;
import com.jmex.physics.Joint;
import com.jmex.physics.JointAxis;
import com.jmex.physics.TranslationalJointAxis;

public class BinaryTranslationalJointAxisModule implements BinaryLoaderModule {
	public String getKey() {
		return TranslationalJointAxis.class.getName();
	}

	public Savable load(InputCapsule inputCapsule) throws IOException {
		Joint joint = JointAxis.readJointFromInputCapsule( inputCapsule );
		return joint.createTranslationalAxis();
	}
}