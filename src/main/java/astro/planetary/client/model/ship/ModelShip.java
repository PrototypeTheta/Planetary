package astro.planetary.client.model.ship;

import astro.lib.tmt.ModelRendererTurbo;
import astro.planetary.client.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class ModelShip extends ModelBase {
    public HashMap<String, ModelRendererTurbo[][]> gunModels = new HashMap<String, ModelRendererTurbo[][]>();
    public ModelRendererTurbo bodyModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo bodyDoorOpenModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo bodyDoorCloseModel[] = new ModelRendererTurbo[0];

    public ModelRendererTurbo primaryPaintBodyModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo secondaryPaintBodyModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo primaryPaintBodyDoorOpenModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo primaryPaintBodyDoorCloseModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo noseModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo leftWingModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo rightWingModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo topWingModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo bayModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo tailModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo propellerModels[][] = new ModelRendererTurbo[0][0]; //Propeller array [numProps][prop blades]
    public ModelRendererTurbo yawFlapModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo pitchFlapLeftModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo pitchFlapRightModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo pitchFlapLeftWingModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo pitchFlapRightWingModel[] = new ModelRendererTurbo[0];

    //Helicopter bits
    public ModelRendererTurbo heliMainRotorModels[][] = new ModelRendererTurbo[0][0]; //Helicopter main rotors model array [numProps][prop blades]
    public Vector3f[] heliMainRotorOrigins = new Vector3f[0]; //Rotation origin of the rotors [numProps]
    public float[] heliRotorSpeeds = new float[0]; //Speed for rotor rotation. Make this negative for reverse blades
    public ModelRendererTurbo heliTailRotorModels[][] = new ModelRendererTurbo[0][0]; //Helicopter tail rotors model array [numProps][prop blades]
    public Vector3f[] heliTailRotorOrigins = new Vector3f[0]; //Rotation origin of the tail rotors [numProps]
    public ModelRendererTurbo skidsModel[] = new ModelRendererTurbo[0]; //Same as landing gear, but for helicopters

    //VTOL bits. They are swapped between when you swap modes
    public ModelRendererTurbo helicopterModeParts[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo planeModeParts[] = new ModelRendererTurbo[0];

    public ModelRendererTurbo bodyWheelModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo tailWheelModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo leftWingWheelModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo rightWingWheelModel[] = new ModelRendererTurbo[0];

    public ModelRendererTurbo tailDoorOpenModel[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo tailDoorCloseModel[] = new ModelRendererTurbo[0];

    public ModelRendererTurbo rightWingPos1Model[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo rightWingPos2Model[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo leftWingPos1Model[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo leftWingPos2Model[] = new ModelRendererTurbo[0];
    public ModelRendererTurbo hudModel[] = new ModelRendererTurbo[0];

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        render();
    }

    protected void translate(ModelRendererTurbo[] model, float x, float y, float z) {
        for (ModelRendererTurbo mod : model) {
            mod.rotationPointX += x;
            mod.rotationPointY += y;
            mod.rotationPointZ += z;
        }
    }

    protected void flip(ModelRendererTurbo[] model) {
        for (ModelRendererTurbo part : model) {
            part.doMirror(false, true, true);
            part.setRotationPoint(part.rotationPointX, -part.rotationPointY, -part.rotationPointZ);
        }
    }

    protected void render() {
        render(bodyModel);
        render(bodyDoorOpenModel);
        render(bodyDoorCloseModel);
        //
        render(primaryPaintBodyModel);
        render(secondaryPaintBodyModel);
        render(primaryPaintBodyDoorOpenModel);
        render(primaryPaintBodyDoorCloseModel);
        //
        render(noseModel);
        render(leftWingModel);
        render(rightWingModel);
        render(topWingModel);
        render(bayModel);
        render(tailModel);
        render(yawFlapModel);
        render(skidsModel);
        render(helicopterModeParts);
        render(planeModeParts);
        render(pitchFlapLeftModel);
        render(pitchFlapRightModel);
        render(pitchFlapLeftWingModel);
        render(pitchFlapRightWingModel);
        render(bodyWheelModel);
        render(tailWheelModel);
        render(leftWingWheelModel);
        render(rightWingWheelModel);
        render(tailDoorOpenModel);
        render(tailDoorCloseModel);
        render(rightWingPos1Model);
        render(rightWingPos2Model);
        render(leftWingPos1Model);
        render(leftWingPos2Model);
        render(hudModel);
        for (ModelRendererTurbo[][] modsOfMods : gunModels.values()) {
            for (ModelRendererTurbo[] mods : modsOfMods) {
                render(mods);
            }
        }
        for (ModelRendererTurbo[] propellerModel : propellerModels) {
            render(propellerModel);
        }
        for (ModelRendererTurbo[] propellerModel : heliMainRotorModels) {
            render(propellerModel);
        }
        for (ModelRendererTurbo[] propellerModel : heliTailRotorModels) {
            render(propellerModel);
        }
    }

    private void render(ModelRendererTurbo[] part) {
        render(part, 0.625F);
    }

    private void render(ModelRendererTurbo[] part, float scale) {
        for (ModelRendererTurbo bit : part) {
            bit.render(scale);
        }
    }

    public void flipAll() {
        flip(bodyModel);
        flip(bodyDoorOpenModel);
        flip(bodyDoorCloseModel);
        //
        flip(primaryPaintBodyModel);
        flip(secondaryPaintBodyModel);
        flip(primaryPaintBodyDoorOpenModel);
        flip(primaryPaintBodyDoorCloseModel);
        //
        flip(noseModel);
        flip(leftWingModel);
        flip(rightWingModel);
        flip(topWingModel);
        flip(bayModel);
        flip(tailModel);
        flip(yawFlapModel);
        flip(skidsModel);
        flip(helicopterModeParts);
        flip(planeModeParts);
        flip(pitchFlapLeftModel);
        flip(pitchFlapRightModel);
        flip(pitchFlapLeftWingModel);
        flip(pitchFlapRightWingModel);
        flip(bodyWheelModel);
        flip(tailWheelModel);
        flip(leftWingWheelModel);
        flip(rightWingWheelModel);
        flip(tailDoorOpenModel);
        flip(tailDoorCloseModel);
        flip(rightWingPos1Model);
        flip(rightWingPos2Model);
        flip(leftWingPos1Model);
        flip(leftWingPos2Model);
        flip(hudModel);
        for (ModelRendererTurbo[][] modsOfMods : gunModels.values()) {
            for (ModelRendererTurbo[] mods : modsOfMods) {
                flip(mods);
            }
        }
        for (ModelRendererTurbo[] propellerModel : propellerModels) {
            flip(propellerModel);
        }
        for (ModelRendererTurbo[] propellerModel : heliMainRotorModels) {
            flip(propellerModel);
        }
        for (ModelRendererTurbo[] propellerModel : heliTailRotorModels) {
            flip(propellerModel);
        }
    }

    public void translateAll(float x, float y, float z) {
        translate(bodyModel, x, y, z);
        translate(bodyDoorOpenModel, x, y, z);
        translate(bodyDoorCloseModel, x, y, z);
        translate(primaryPaintBodyModel, x, y, z);
        translate(secondaryPaintBodyModel, x, y, z);
        translate(primaryPaintBodyDoorOpenModel, x, y, z);
        translate(primaryPaintBodyDoorCloseModel, x, y, z);
        translate(noseModel, x, y, z);
        translate(leftWingModel, x, y, z);
        translate(rightWingModel, x, y, z);
        translate(topWingModel, x, y, z);
        translate(bayModel, x, y, z);
        translate(tailModel, x, y, z);
        translate(yawFlapModel, x, y, z);
        translate(skidsModel, x, y, z);
        translate(helicopterModeParts, x, y, z);
        translate(planeModeParts, x, y, z);
        translate(pitchFlapLeftModel, x, y, z);
        translate(pitchFlapRightModel, x, y, z);
        translate(pitchFlapLeftWingModel, x, y, z);
        translate(pitchFlapRightWingModel, x, y, z);
        translate(bodyWheelModel, x, y, z);
        translate(tailWheelModel, x, y, z);
        translate(leftWingWheelModel, x, y, z);
        translate(rightWingWheelModel, x, y, z);
        translate(tailDoorOpenModel, x, y, z);
        translate(tailDoorCloseModel, x, y, z);
        translate(rightWingPos1Model, x, y, z);
        translate(rightWingPos2Model, x, y, z);
        translate(leftWingPos1Model, x, y, z);
        translate(leftWingPos2Model, x, y, z);
        translate(hudModel, x, y, z);

        for (ModelRendererTurbo[] mods : propellerModels) {
            translate(mods, x, y, z);
        }
        for (ModelRendererTurbo[] mods : heliMainRotorModels) {
            translate(mods, x, y, z);
        }
        for (ModelRendererTurbo[] mods : heliTailRotorModels) {
            translate(mods, x, y, z);
        }
        for (Vector3f o : heliMainRotorOrigins)
            Vector3f.add(o, new Vector3f(x / 16F, y / 16F, z / 16F), o);
        for (Vector3f o : heliTailRotorOrigins)
            Vector3f.add(o, new Vector3f(x / 16F, y / 16F, z / 16F), o);
        //
        for (ModelRendererTurbo[][] modsOfMods : gunModels.values()) {
            for (ModelRendererTurbo[] mods : modsOfMods) {
                translate(mods, x, y, z);
            }
        }
    }

    public void translateAll(int x, int y, int z) {
        translateAll((float) x, (float) y, (float) z);
    }
}