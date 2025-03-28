package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RailState {
   private final World world;
   private final BlockPos pos;
   private final AbstractRailBlock block;
   private BlockState newState;
   private final boolean disableCorners;
   private final List<BlockPos> connectedRails = Lists.newArrayList();
   private final boolean canMakeSlopes;

   public RailState(World worldIn, BlockPos posIn, BlockState state) {
      this.world = worldIn;
      this.pos = posIn;
      this.newState = state;
      this.block = (AbstractRailBlock)state.getBlock();
      RailShape railshape = this.block.getRailDirection(newState, worldIn, posIn, null);
      this.disableCorners = !this.block.isFlexibleRail(newState, worldIn, posIn);
      this.canMakeSlopes = this.block.canMakeSlopes(newState, worldIn, posIn);
      this.reset(railshape);
   }

   public List<BlockPos> getConnectedRails() {
      return this.connectedRails;
   }

   private void reset(RailShape shape) {
      this.connectedRails.clear();
      switch(shape) {
      case NORTH_SOUTH:
         this.connectedRails.add(this.pos.north());
         this.connectedRails.add(this.pos.south());
         break;
      case EAST_WEST:
         this.connectedRails.add(this.pos.west());
         this.connectedRails.add(this.pos.east());
         break;
      case ASCENDING_EAST:
         this.connectedRails.add(this.pos.west());
         this.connectedRails.add(this.pos.east().up());
         break;
      case ASCENDING_WEST:
         this.connectedRails.add(this.pos.west().up());
         this.connectedRails.add(this.pos.east());
         break;
      case ASCENDING_NORTH:
         this.connectedRails.add(this.pos.north().up());
         this.connectedRails.add(this.pos.south());
         break;
      case ASCENDING_SOUTH:
         this.connectedRails.add(this.pos.north());
         this.connectedRails.add(this.pos.south().up());
         break;
      case SOUTH_EAST:
         this.connectedRails.add(this.pos.east());
         this.connectedRails.add(this.pos.south());
         break;
      case SOUTH_WEST:
         this.connectedRails.add(this.pos.west());
         this.connectedRails.add(this.pos.south());
         break;
      case NORTH_WEST:
         this.connectedRails.add(this.pos.west());
         this.connectedRails.add(this.pos.north());
         break;
      case NORTH_EAST:
         this.connectedRails.add(this.pos.east());
         this.connectedRails.add(this.pos.north());
      }

   }

   private void checkConnected() {
      for(int i = 0; i < this.connectedRails.size(); ++i) {
         RailState railstate = this.createForAdjacent(this.connectedRails.get(i));
         if (railstate != null && railstate.isConnectedTo(this)) {
            this.connectedRails.set(i, railstate.pos);
         } else {
            this.connectedRails.remove(i--);
         }
      }

   }

   private boolean isAdjacentRail(BlockPos posIn) {
      return AbstractRailBlock.isRail(this.world, posIn) || AbstractRailBlock.isRail(this.world, posIn.up()) || AbstractRailBlock.isRail(this.world, posIn.down());
   }

   @Nullable
   private RailState createForAdjacent(BlockPos posIn) {
      BlockState blockstate = this.world.getBlockState(posIn);
      if (AbstractRailBlock.isRail(blockstate)) {
         return new RailState(this.world, posIn, blockstate);
      } else {
         BlockPos lvt_2_1_ = posIn.up();
         blockstate = this.world.getBlockState(lvt_2_1_);
         if (AbstractRailBlock.isRail(blockstate)) {
            return new RailState(this.world, lvt_2_1_, blockstate);
         } else {
            lvt_2_1_ = posIn.down();
            blockstate = this.world.getBlockState(lvt_2_1_);
            return AbstractRailBlock.isRail(blockstate) ? new RailState(this.world, lvt_2_1_, blockstate) : null;
         }
      }
   }

   private boolean isConnectedTo(RailState state) {
      return this.isConnectedTo(state.pos);
   }

   private boolean isConnectedTo(BlockPos posIn) {
      for(int i = 0; i < this.connectedRails.size(); ++i) {
         BlockPos blockpos = this.connectedRails.get(i);
         if (blockpos.getX() == posIn.getX() && blockpos.getZ() == posIn.getZ()) {
            return true;
         }
      }

      return false;
   }

   protected int countAdjacentRails() {
      int i = 0;

      for(Direction direction : Direction.Plane.HORIZONTAL) {
         if (this.isAdjacentRail(this.pos.offset(direction))) {
            ++i;
         }
      }

      return i;
   }

   private boolean func_196905_c(RailState state) {
      return this.isConnectedTo(state) || this.connectedRails.size() != 2;
   }

   private void func_208510_c(RailState state) {
      this.connectedRails.add(state.pos);
      BlockPos blockpos = this.pos.north();
      BlockPos blockpos1 = this.pos.south();
      BlockPos blockpos2 = this.pos.west();
      BlockPos blockpos3 = this.pos.east();
      boolean flag = this.isConnectedTo(blockpos);
      boolean flag1 = this.isConnectedTo(blockpos1);
      boolean flag2 = this.isConnectedTo(blockpos2);
      boolean flag3 = this.isConnectedTo(blockpos3);
      RailShape railshape = null;
      if (flag || flag1) {
         railshape = RailShape.NORTH_SOUTH;
      }

      if (flag2 || flag3) {
         railshape = RailShape.EAST_WEST;
      }

      if (!this.disableCorners) {
         if (flag1 && flag3 && !flag && !flag2) {
            railshape = RailShape.SOUTH_EAST;
         }

         if (flag1 && flag2 && !flag && !flag3) {
            railshape = RailShape.SOUTH_WEST;
         }

         if (flag && flag2 && !flag1 && !flag3) {
            railshape = RailShape.NORTH_WEST;
         }

         if (flag && flag3 && !flag1 && !flag2) {
            railshape = RailShape.NORTH_EAST;
         }
      }

      if (railshape == RailShape.NORTH_SOUTH && canMakeSlopes) {
         if (AbstractRailBlock.isRail(this.world, blockpos.up())) {
            railshape = RailShape.ASCENDING_NORTH;
         }

         if (AbstractRailBlock.isRail(this.world, blockpos1.up())) {
            railshape = RailShape.ASCENDING_SOUTH;
         }
      }

      if (railshape == RailShape.EAST_WEST && canMakeSlopes) {
         if (AbstractRailBlock.isRail(this.world, blockpos3.up())) {
            railshape = RailShape.ASCENDING_EAST;
         }

         if (AbstractRailBlock.isRail(this.world, blockpos2.up())) {
            railshape = RailShape.ASCENDING_WEST;
         }
      }

      if (railshape == null) {
         railshape = RailShape.NORTH_SOUTH;
      }

      this.newState = this.newState.with(this.block.getShapeProperty(), railshape);
      this.world.setBlockState(this.pos, this.newState, 3);
   }

   private boolean func_208512_d(BlockPos posIn) {
      RailState railstate = this.createForAdjacent(posIn);
      if (railstate == null) {
         return false;
      } else {
         railstate.checkConnected();
         return railstate.func_196905_c(this);
      }
   }

   public RailState placeRail(boolean p_226941_1_, boolean p_226941_2_, RailShape p_226941_3_) {
      BlockPos blockpos = this.pos.north();
      BlockPos blockpos1 = this.pos.south();
      BlockPos blockpos2 = this.pos.west();
      BlockPos blockpos3 = this.pos.east();
      boolean flag = this.func_208512_d(blockpos);
      boolean flag1 = this.func_208512_d(blockpos1);
      boolean flag2 = this.func_208512_d(blockpos2);
      boolean flag3 = this.func_208512_d(blockpos3);
      RailShape railshape = null;
      boolean flag4 = flag || flag1;
      boolean flag5 = flag2 || flag3;
      if (flag4 && !flag5) {
         railshape = RailShape.NORTH_SOUTH;
      }

      if (flag5 && !flag4) {
         railshape = RailShape.EAST_WEST;
      }

      boolean flag6 = flag1 && flag3;
      boolean flag7 = flag1 && flag2;
      boolean flag8 = flag && flag3;
      boolean flag9 = flag && flag2;
      if (!this.disableCorners) {
         if (flag6 && !flag && !flag2) {
            railshape = RailShape.SOUTH_EAST;
         }

         if (flag7 && !flag && !flag3) {
            railshape = RailShape.SOUTH_WEST;
         }

         if (flag9 && !flag1 && !flag3) {
            railshape = RailShape.NORTH_WEST;
         }

         if (flag8 && !flag1 && !flag2) {
            railshape = RailShape.NORTH_EAST;
         }
      }

      if (railshape == null) {
         if (flag4 && flag5) {
            railshape = p_226941_3_;
         } else if (flag4) {
            railshape = RailShape.NORTH_SOUTH;
         } else if (flag5) {
            railshape = RailShape.EAST_WEST;
         }

         if (!this.disableCorners) {
            if (p_226941_1_) {
               if (flag6) {
                  railshape = RailShape.SOUTH_EAST;
               }

               if (flag7) {
                  railshape = RailShape.SOUTH_WEST;
               }

               if (flag8) {
                  railshape = RailShape.NORTH_EAST;
               }

               if (flag9) {
                  railshape = RailShape.NORTH_WEST;
               }
            } else {
               if (flag9) {
                  railshape = RailShape.NORTH_WEST;
               }

               if (flag8) {
                  railshape = RailShape.NORTH_EAST;
               }

               if (flag7) {
                  railshape = RailShape.SOUTH_WEST;
               }

               if (flag6) {
                  railshape = RailShape.SOUTH_EAST;
               }
            }
         }
      }

      if (railshape == RailShape.NORTH_SOUTH && canMakeSlopes) {
         if (AbstractRailBlock.isRail(this.world, blockpos.up())) {
            railshape = RailShape.ASCENDING_NORTH;
         }

         if (AbstractRailBlock.isRail(this.world, blockpos1.up())) {
            railshape = RailShape.ASCENDING_SOUTH;
         }
      }

      if (railshape == RailShape.EAST_WEST && canMakeSlopes) {
         if (AbstractRailBlock.isRail(this.world, blockpos3.up())) {
            railshape = RailShape.ASCENDING_EAST;
         }

         if (AbstractRailBlock.isRail(this.world, blockpos2.up())) {
            railshape = RailShape.ASCENDING_WEST;
         }
      }

      if (railshape == null) {
         railshape = p_226941_3_;
      }

      this.reset(railshape);
      this.newState = this.newState.with(this.block.getShapeProperty(), railshape);
      if (p_226941_2_ || this.world.getBlockState(this.pos) != this.newState) {
         this.world.setBlockState(this.pos, this.newState, 3);

         for(int i = 0; i < this.connectedRails.size(); ++i) {
            RailState railstate = this.createForAdjacent(this.connectedRails.get(i));
            if (railstate != null) {
               railstate.checkConnected();
               if (railstate.func_196905_c(this)) {
                  railstate.func_208510_c(this);
               }
            }
         }
      }

      return this;
   }

   public BlockState getNewState() {
      return this.newState;
   }
}