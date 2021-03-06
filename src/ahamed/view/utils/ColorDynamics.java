/**
 * Copyright 2014 Riyaz Ahamed
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package ahamed.view.utils;

import android.graphics.Color;


/**
 * <p>
 *         {@link ColorDynamics} class used to Animate the Colors in the View.<br>
 *         Uses {@link Dynamics} to update ColorValues
 *         </p>
 * 
 * @author Riyaz Ahamed M <br>
 *         Send Feedbacks to dev.ahamed(at)outlook.com
 */
public class ColorDynamics {

    Dynamics alpha = new Dynamics(50, 0.8f);
    Dynamics red = new Dynamics(50, 0.8f);
    Dynamics green = new Dynamics(50, 0.8f);
    Dynamics blue = new Dynamics(50, 0.8f);

    public int getColor() {
        final int a = clamp(alpha.getPosition());
        final int r = clamp(red.getPosition());
        final int g = clamp(green.getPosition());
        final int b = clamp(blue.getPosition());
        return Color.argb(a, r, g, b);
    }

    public void setColor(int color, long now) {
        alpha.setPosition(Color.alpha(color), now);
        red.setPosition(Color.red(color), now);
        green.setPosition(Color.green(color), now);
        blue.setPosition(Color.blue(color), now);
    }

    public void setTargetColor(int color, long now) {
        alpha.setTargetPosition(Color.alpha(color), now);
        red.setTargetPosition(Color.red(color), now);
        green.setTargetPosition(Color.green(color), now);
        blue.setTargetPosition(Color.blue(color), now);
    }

    public void update(long now) {
        alpha.update(now);
        red.update(now);
        green.update(now);
        blue.update(now);
    }

    public boolean isAtRest() {
        return alpha.isAtRest() && red.isAtRest() && green.isAtRest() && blue.isAtRest();
    }

    private int clamp(float value) {
        if (value < 0) {
            return 0;
        } else if (value > 0xFF) {
            return 0xFF;
        } else {
            return (int) value;
        }
    }
}
