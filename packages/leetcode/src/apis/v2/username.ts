import { OpenAPIRoute } from 'chanfana'

import { usernameBadge } from '../../services/username'
import {
  shieldExtraConfigsParams,
  successfulResponse,
  usernameParam,
} from '../common'

export class Username extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode'],
    request: {
      params: usernameParam,
      query: shieldExtraConfigsParams,
    },
    responses: {
      200: successfulResponse,
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { ...shieldExtraConfigs } = data.query
    const shield = await usernameBadge(username, shieldExtraConfigs)
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
